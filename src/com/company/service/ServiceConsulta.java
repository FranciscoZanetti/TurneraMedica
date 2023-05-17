/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.DAOConsulta;
import com.company.entidades.Consulta;
import com.company.entidades.ConsultorioMedico;
import com.company.entidades.Paciente;
import com.company.validaciones.ValidarLocalDate;
import com.company.validaciones.ValidarNumeroPositivo;
import com.company.validaciones.ValidarSoloLetras;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.time.temporal.TemporalAdjusters;

/**
 *
 * @author User
 */
public class ServiceConsulta {
    private DAOConsulta daoConsulta;
    
    public ServiceConsulta(){
        daoConsulta = new DAOConsulta();
    }
    
    public void ordenarConsultas(ArrayList<Consulta> consultas){
        Collections.sort(consultas, new Comparator<Consulta>(){
            @Override
            public int compare(Consulta consulta1, Consulta consulta2){
                int dateComparison = consulta1.getFecha().compareTo(consulta2.getFecha());
                if (dateComparison == 0){
                    return consulta1.getHora().compareTo(consulta2.getHora());
                }
                return dateComparison;
            }
        });
    }
    
    public void ordenarFechas(ArrayList<LocalDate> fechas){
        Collections.sort(fechas, new Comparator<LocalDate>(){
            @Override
            public int compare(LocalDate fecha1, LocalDate fecha2){
                return fecha1.compareTo(fecha2);
            }
        });
    }
    
    public void create(Consulta consulta) throws ServiceException{
        try{
            daoConsulta.create(consulta);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void delete(int primaryKey) throws ServiceException{
        try{
            daoConsulta.delete(primaryKey);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void deleteByMedico(int medico) throws ServiceException{
        try{
            daoConsulta.deleteByMedico(medico);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void deleteMissmatches(int dni)throws ServiceException{
        try{
            ArrayList<Consulta> consultas = searchAllByMedico(dni, 1);
            ServiceConsultorioMedico serviceConsultorioMedico = new ServiceConsultorioMedico();
            ArrayList<ConsultorioMedico> consultoriosMedicos = serviceConsultorioMedico.searchAll(dni);
            
            Locale locale = new Locale("es", "ES");
            TextStyle style = TextStyle.FULL;
            
            for(Consulta consulta: consultas){
                int verifier = 0;
                if (consultoriosMedicos.isEmpty()){
                    try{
                        delete(consulta.getId());
                    }catch(ServiceException exception){
                        throw new ServiceException(exception.getMessage());
                    }
                }
                else{
                    for (ConsultorioMedico consultorioMedico : consultoriosMedicos){
                        String nombreDiaSemana = consulta.getFecha().getDayOfWeek().getDisplayName(style, locale);

                        if (!nombreDiaSemana.equals(consultorioMedico.getDia())){
                            verifier = 1;
                        }
                        else{
                            if (consulta.getHora().isBefore(consultorioMedico.getEntrada()) || consulta.getHora().isAfter(consultorioMedico.getSalida())){
                                verifier = 1;
                            }
                        }
                    }
                    if (verifier == 1){
                        try{
                            delete(consulta.getId());
                        }catch(ServiceException exception){
                            throw new ServiceException(exception.getMessage());
                        }
                    }
                }
                
            }
            
        }catch(ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void deactivate(Consulta consulta) throws ServiceException{
        try{
            daoConsulta.deactivate(consulta);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public ArrayList<Consulta> searchAll(int activo, int status) throws ServiceException{
        ArrayList<Consulta> consultas = new ArrayList<>();
        try {
            consultas = daoConsulta.searchAll(activo, status);
            ordenarConsultas(consultas);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public ArrayList<Consulta> searchAllBetween(int activo, LocalDate start, LocalDate end) throws ServiceException{
        ArrayList<Consulta> consultas = new ArrayList<>();
        try {
            consultas = daoConsulta.searchAllBetween(activo, start, end);
            ordenarConsultas(consultas);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public ArrayList<Consulta> searchAllBetweenByMedico(int dni, LocalDate start, LocalDate end) throws ServiceException{
        ArrayList<Consulta> consultas = new ArrayList<>();
        try {
            consultas = daoConsulta.searchAllBetweenByMedico(dni, start, end);
            ordenarConsultas(consultas);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public int recaudacionByMedicoBetween(int dni, LocalDate start, LocalDate end) throws ServiceException{
        ArrayList<Consulta> consultas = new ArrayList<>();
        int recaudacion = 0;
        try{
            consultas = searchAllBetweenByMedico(dni, start, end);
            for (Consulta consulta : consultas){
                recaudacion += consulta.getPrecio();
            }
            return recaudacion;
        }catch(ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public ArrayList<Consulta> searchAllByMedico(int dni, int status) throws ServiceException{
        ArrayList<Consulta> consultas = new ArrayList<>();
        try {
            consultas = daoConsulta.searchAllByMedico(dni, status);
            ordenarConsultas(consultas);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public int recaudacionByMedico(int dni) throws ServiceException{
        ArrayList<Consulta> consultas = new ArrayList<>();
        int recaudacion = 0;
        try{
            consultas = searchAllByMedico(dni, 0);
            for (Consulta consulta : consultas){
                recaudacion += consulta.getPrecio();
            }
            return recaudacion;
        }catch(ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public ArrayList<Consulta> searchAllByPaciente(int dni, int status) throws ServiceException{
        ArrayList<Consulta> consultas = new ArrayList<>();
        try {
            consultas = daoConsulta.searchAllByPaciente(dni, status);
            ordenarConsultas(consultas);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    
    public int incomeGeneratedByMedico(ArrayList<Consulta> consultas){
        int acum = 0;
        for(Consulta consulta : consultas){
            acum = acum + consulta.getPrecio();
        }
        return acum;
    }
    
    public ArrayList<String> getAllConsultorios(ArrayList<Consulta> consultas){
        ArrayList<String> consultorios = new ArrayList<String>();
        for(Consulta consulta : consultas){
            if (consultorios.isEmpty()){
                consultorios.add(consulta.getConsultorio());
            }else{
                if (!consultorios.contains(consulta.getConsultorio())){
                    consultorios.add(consulta.getConsultorio());
                }
            }
        }
        return consultorios;
    }
    
    public void cobrarConsultas() throws ServiceException{
        ArrayList<Consulta> consultas = searchAll(1, 1);
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        for (Consulta consulta : consultas){
            if (consulta.getFecha().isBefore(today)){
                deactivate(consulta);
            }
            if (consulta.getFecha().isEqual(today)){
                if (consulta.getHora().isBefore(now)){
                    deactivate(consulta);
                }
            }
        }
    }
    
    public ArrayList<Consulta> generateArrayConsultas(Paciente paciente, ArrayList<Consulta> consultas, ArrayList<ConsultorioMedico> consultoriosMedicos){
        ArrayList<Consulta> consultasNew = new ArrayList<>();
        LocalDate now = LocalDate.now();
        Date today = Date.valueOf(now);
        Date lastDate = Date.valueOf(now.plusMonths(1));
        long t1 = today.getTime();
        long t2 = lastDate.getTime();
        for (long i=t1; i<=t2; i+=86400000){
            LocalDate date = Instant.ofEpochMilli(i)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            for (ConsultorioMedico consultorioMedico : consultoriosMedicos){
                String dayOfWeek = new SimpleDateFormat("EEEE", Locale.forLanguageTag("es-AR")).format(Date.valueOf(date));
                System.out.println(dayOfWeek + " | " + consultorioMedico.getDia().toLowerCase());
                System.out.println(dayOfWeek.equals(consultorioMedico.getDia().toLowerCase()));
                if (dayOfWeek.equals(consultorioMedico.getDia().toLowerCase())){
                    System.out.println("DAYOFWEEK " + dayOfWeek);
                    
                    LocalTime entrada = consultorioMedico.getEntrada();
                    LocalTime salida = consultorioMedico.getSalida();
                    
                    LocalTime hora = entrada;
                    do{
                        if (today.equals(Date.valueOf(date))){
                            if (hora.isAfter(LocalTime.now())){
                                Consulta consulta = new  Consulta();
                                consulta.setConsultorio(consultorioMedico.getConsultorio());
                                consulta.setMedico(consultorioMedico.getMedico());
                                consulta.setPaciente(paciente);
                                consulta.setFecha(date);
                                consulta.setHora(hora);
                                consulta.setPrecio(paciente, consultorioMedico.getMedico());
                                consultasNew.add(consulta);
                            }
                        }else{
                            Consulta consulta = new  Consulta();
                            consulta.setConsultorio(consultorioMedico.getConsultorio());
                            consulta.setMedico(consultorioMedico.getMedico());
                            consulta.setPaciente(paciente);
                            consulta.setFecha(date);
                            consulta.setHora(hora);
                            consulta.setPrecio(paciente, consultorioMedico.getMedico());
                            consultasNew.add(consulta);
                        }
                        
                        hora = hora.plusMinutes(30);
                    }while (hora.isBefore(salida));
                }
            }
        }
        
        for (Consulta consulta : consultas) {
            Iterator<Consulta> iterator = consultasNew.iterator();
            while (iterator.hasNext()) {
                Consulta consultaNew = iterator.next();
                if (consultaNew.getFecha().equals(consulta.getFecha()) && consultaNew.getHora().equals(consulta.getHora())) {
                    iterator.remove();
                }
            }
        }

        
        return consultasNew;
    }
    
    public ArrayList<String> generateArrayFechas(ArrayList<Consulta> consultas){
        ArrayList<LocalDate> fechas = new ArrayList<>();
        LocalDate nowPlusMonth = Date.valueOf(LocalDate.now().plusMonths(1)).toLocalDate();
        for (Consulta consulta: consultas){
            LocalDate date = consulta.getFecha();
            if (date.isBefore(nowPlusMonth) || date.isEqual(nowPlusMonth)){
                fechas.add(date);
            }
        }
        HashSet<LocalDate> fechasHash = new HashSet<>(fechas);
        fechas = new ArrayList<>(fechasHash);
        ordenarFechas(fechas);
        
        ArrayList<String> fechasString = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM. yyyy", new Locale("es", "ES"));
        for (LocalDate fecha: fechas){
            fechasString.add(fecha.format(formatter));
        }
        return fechasString;
    }
}

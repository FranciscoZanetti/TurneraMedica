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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/**
 *
 * @author User
 */
public class ServiceConsulta {
    private DAOConsulta daoConsulta;
    
    public ServiceConsulta(){
        daoConsulta = new DAOConsulta();
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
    
    public void deactivate(Consulta consulta) throws ServiceException{
        try{
            daoConsulta.deactivate(consulta);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public ArrayList<Consulta> searchAll(int activo) throws ServiceException{
        ArrayList<Consulta> consultas;
        try {
            consultas = daoConsulta.searchAll(activo);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public ArrayList<Consulta> serchAllBetween(int activo, LocalDate start, LocalDate end) throws ServiceException{
        ArrayList<Consulta> consultas;
        try {
            consultas = daoConsulta.searchAllBetween(activo, start, end);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public ArrayList<Consulta> serchAllBetweenByMedico(int dni, LocalDate start, LocalDate end) throws ServiceException{
        ArrayList<Consulta> consultas;
        try {
            consultas = daoConsulta.searchAllBetweenByMedico(dni, start, end);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public ArrayList<Consulta> serchAllByMedico(int dni, int status) throws ServiceException{
        ArrayList<Consulta> consultas;
        try {
            consultas = daoConsulta.searchAllByMedico(dni, status);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    public ArrayList<Consulta> serchAllByPaciente(int dni, int status) throws ServiceException{
        ArrayList<Consulta> consultas;
        try {
            consultas = daoConsulta.searchAllByPaciente(dni, status);
            return consultas;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    
    public int incomeGeneratedByMedico(ArrayList<Consulta> consultas){
        int acum = 0;
        for(Consulta consulta : consultas){
            acum = acum + consulta.getMedico().getHonorarios();
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
    
    public void cobrarConsultas(ArrayList<Consulta> consultas) throws ServiceException{
        ServiceConsulta serviceConsulta = new ServiceConsulta();
        LocalDate today = LocalDate.now();
        for (Consulta consulta : consultas){
            if (consulta.getFecha().isBefore(today)){
                serviceConsulta.deactivate(consulta);
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
}

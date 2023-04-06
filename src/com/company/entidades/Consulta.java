package com.company.entidades;

import com.company.service.ServiceConsulta;
import com.company.service.ServiceException;
import com.company.util.Fecha;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class Consulta {
    private int id;
    private Medico medico;
    private Paciente paciente;
    private String consultorio;
    private LocalDate fecha;
    private LocalTime hora;
    private int precio;

    public Consulta (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    public Date getFechaAsDate(){
        return Date.valueOf(fecha);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }
    
    public Time getHoraAsTime(){
        return Time.valueOf(hora);
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public String getConsultorio(){
        return consultorio;
    }
    
    public void setConsultorio(String consultorio){
        this.consultorio = consultorio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public void setPrecio(Paciente paciente, Medico medico){
        if (paciente.obrasocial.equals(medico.obrasocial)){
            this.precio = (int) (medico.getHonorarios() * 0.5);
        }else{
            this.precio = medico.getHonorarios();
        }
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", medico=" + medico + ", paciente=" + paciente + ", consultorio=" + consultorio + ", fecha=" + fecha + ", hora=" + hora + ", precio=" + precio + '}';
    }
    
//    public int incomeGeneratedByMedico(ArrayList<Consulta> consultas){
//        int acum = 0;
//        for(Consulta consulta : consultas){
//            acum = acum + consulta.getMedico().getHonorarios();
//        }
//        return acum;
//    }
//    
//    public int incomeGenerated(ArrayList<Integer> montos){
//        int acum = 0;
//        for(Integer monto : montos){
//            acum = acum + monto;
//        }
//        return acum;
//    }
//    
//    public ArrayList<String> getAllConsultorios(ArrayList<Consulta> consultas){
//        ArrayList<String> consultorios = new ArrayList<String>();
//        for(Consulta consulta : consultas){
//            if (consultorios.isEmpty()){
//                consultorios.add(consulta.getConsultorio());
//            }else{
//                if (!consultorios.contains(consulta.getConsultorio())){
//                    consultorios.add(consulta.getConsultorio());
//                }
//            }
//        }
//        return consultorios;
//    }
//    
//    public void cobrarConsultas(ArrayList<Consulta> consultas) throws ServiceException{
//        ServiceConsulta serviceConsulta = new ServiceConsulta();
//        LocalDate today = LocalDate.now();
//        for (Consulta consulta : consultas){
//            if (consulta.getFecha().isBefore(today)){
//                serviceConsulta.deactivate(consulta);
//            }
//        }
//    }
//    
//    public ArrayList<Consulta> generateArrayConsultas(Paciente paciente, ArrayList<Consulta> consultas, ArrayList<ConsultorioMedico> consultoriosMedicos){
//        ArrayList<Consulta> consultasNew = new ArrayList<>();
//        LocalDate now = LocalDate.now();
//        Date today = Date.valueOf(now);
//        Date lastDate = Date.valueOf(now.plusMonths(1));
//        long t1 = today.getTime();
//        long t2 = lastDate.getTime();
//        for (long i=t1; i<=t2; i+=86400000){
//            LocalDate date = Instant.ofEpochMilli(i)
//                    .atZone(ZoneId.systemDefault())
//                    .toLocalDate();
//            for (ConsultorioMedico consultorioMedico : consultoriosMedicos){
//                String dayOfWeek = new SimpleDateFormat("EEEE", Locale.forLanguageTag("es-AR")).format(Date.valueOf(date));
//                System.out.println(dayOfWeek + " | " + consultorioMedico.getDia().toLowerCase());
//                System.out.println(dayOfWeek.equals(consultorioMedico.getDia().toLowerCase()));
//                if (dayOfWeek.equals(consultorioMedico.getDia().toLowerCase())){
//                    System.out.println("DAYOFWEEK " + dayOfWeek);
//                    
//                    LocalTime entrada = consultorioMedico.getEntrada();
//                    LocalTime salida = consultorioMedico.getSalida();
//                    
//                    LocalTime hora = entrada;
//                    do{
//                        Consulta consulta = new  Consulta();
//                        consulta.setConsultorio(consultorioMedico.getConsultorio());
//                        consulta.setMedico(consultorioMedico.getMedico());
//                        consulta.setPaciente(paciente);
//                        consulta.setFecha(date);
//                        consulta.setHora(hora);
//                        consultasNew.add(consulta);
//                        
//                        hora = hora.plusMinutes(30);
//                    }while (hora.isBefore(salida));
//                }
//            }
//        }
//        
//        for (Consulta consulta : consultas) {
//            Iterator<Consulta> iterator = consultasNew.iterator();
//            while (iterator.hasNext()) {
//                Consulta consultaNew = iterator.next();
//                if (consultaNew.getFecha().equals(consulta.getFecha()) && consultaNew.getHora().equals(consulta.getHora())) {
//                    iterator.remove();
//                }
//            }
//        }
//
//        
//        return consultasNew;
//    }
}

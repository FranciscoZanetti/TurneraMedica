/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.util;

import com.company.entidades.ConsultorioMedico;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author User
 */
public class Fecha {
    private LocalDate fecha;
    private ArrayList<LocalTime> horas;
    private String consultorio;
    
    public Fecha(){}
    
//    public Fecha(LocalDate fecha, String consultorio){
//        this.fecha = fecha;
//    }
    
    public LocalDate getFecha(){
        return fecha;
    }
    
    public Date getFechaAsDate(){
        return Date.valueOf(fecha);
    }
    
    public ArrayList<LocalTime> getHoras(){
        return horas;
    }
    
    public ArrayList<Time> getHorasAsTime(){
        ArrayList<Time> horasTime = new ArrayList<Time>();
        for (LocalTime hora: horas){
            horasTime.add(Time.valueOf(hora));
        }
        return horasTime;
    }

    public String getConsultorio() {
        return consultorio;
    }
    
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }
    
    public void setHoras(LocalTime entrada, LocalTime salida){
        this.horas.add(entrada);
        LocalTime hora = entrada;
        do{
            hora.plusMinutes(30);
            this.horas.add(hora);
        }while ( hora.isBefore(salida) && hora !=salida );
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }
    
    public ArrayList<Fecha> generateArrayFechas(ArrayList<ConsultorioMedico> consultoriosMedicos){
        ArrayList<Fecha> fechas = new ArrayList<Fecha>();
        Fecha fecha = null;
        LocalDate now = LocalDate.now();
        Date today = Date.valueOf(now);
        Date lastDate = Date.valueOf(now.plusMonths(1));
        long t1 = today.getTime();
        long t2 = lastDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dayOfWeek;
//        Date aux;
        for (long i=t1; i<=t2; i+=86400000){
            fecha = new Fecha();
//                aux = Date.valueOf(format.format(i));
            fecha.setFecha(LocalDate.parse(format.format(i)));
            for (ConsultorioMedico consultorioMedico : consultoriosMedicos){
                dayOfWeek = fecha.getFechaAsDayString();
                if (dayOfWeek == consultorioMedico.getDia()){
                    fecha.setHoras(consultorioMedico.getEntrada(), consultorioMedico.getSalida());
                    fecha.setConsultorio(consultorioMedico.getConsultorio());
                }
            }
            fechas.add(fecha);
        }
        return fechas;
    }
    
    public String getFechaAsDayString(){
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.forLanguageTag("es-AR")).format(Date.valueOf(fecha));
        return dayOfWeek;
    }
    
    public boolean compareDayOfWeek(String day1, String day2){
        boolean result = false;
        if (day1 == day2){
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Fecha{" + "fecha=" + fecha + ", horas=" + horas + ", consultorio=" + consultorio + '}';
    }
    
}

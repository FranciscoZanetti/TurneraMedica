/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.entidades;

import com.company.util.Fecha;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class ConsultorioMedico {
    private int id;
    private String consultorio;
    private Medico medico;
    private String dia;
    private LocalTime entrada;
    private LocalTime salida;
    
    public int getId(){
        return id;
    }
    
    public String getConsultorio(){
        return consultorio;
    }
    
    public Medico getMedico(){
        return medico;
    }
    
    public String getDia(){
        return dia;
    }
    
    public LocalTime getEntrada(){
        return entrada;
    }
    
    public Time getEntradaAsTime(){
        return Time.valueOf(entrada);
    }
    
    public LocalTime getSalida(){
        return salida;
    }
    
    public Time getSalidaAsTime(){
        return Time.valueOf(salida);
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public void setConsultorio(String consultorio){
        this.consultorio = consultorio;
    }
    
    public void setMedico(Medico medico){
        this.medico = medico;
    }
    
    public void setDia(String dia){
        this.dia = dia;
    }
    
    public void setEntrada(LocalTime entrada){
        this.entrada = entrada;
    }
    
    public void setSalida(LocalTime salida){
        this.salida = salida;
    }
    
    
//    public ArrayList<String> getDays(ArrayList<ConsultorioMedico> consultoriosMedicos){
//        ArrayList<String> days;
//        
//    }

    @Override
    public String toString() {
        return "ConsultorioMedico{" + "id=" + id + ", consultorio=" + consultorio + ", medico=" + medico + ", dia=" + dia + ", entrada=" + entrada + ", salida=" + salida + '}';
    }
}

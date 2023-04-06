package com.company.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Paciente extends Persona{
    private String sexo;
    private LocalDate nacimiento;

    public Paciente(){
        super();
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return Long.valueOf(ChronoUnit.YEARS.between(nacimiento, LocalDate.now())).intValue();
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public Date getNacimientoAsDate(){
        return Date.valueOf(nacimiento);
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    @Override
    public String toString() {
        return
//                "Paciente{" +
//                "DNI=" + DNI +
//                ", nombre='" + nombre + '\'' +
//                ", apellido='" + apellido + '\'' +
//                ", sexo='" + sexo + '\'' +
//                ", edad=" + edad +
//                ", nacimiento=" + nacimiento +
//                ", obrasocial='" + obrasocial + '\'' +
//                '}';
                nombre + " " + apellido + " - DNI: " + DNI + " - Sexo: " + sexo + " - Edad: " + getEdad() + " - Fecha de nacimiento: " + nacimiento + " - Obra Social: " + obrasocial;
    }


    public boolean esHombre(){
        if (sexo == "hombre"){
            return true;
        }else{
            return false;
        }
    }

    public boolean esMujer(){
        if (sexo == "mujer"){
            return true;
        }else{
            return false;
        }
    }

}

package com.company.entidades;

public class Medico extends Persona{
    private int honorarios;

    public Medico (){
        super();
    }

    public int getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(int honorarios) {
        this.honorarios = honorarios;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "DNI=" + DNI +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", obrasocial='" + obrasocial + '\'' +
                ", honorarios=" + honorarios +
                '}';
    }
}

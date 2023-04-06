package com.company.entidades;

public class Persona {
    protected int DNI;
    protected String password;
    protected String nombre;
    protected String apellido;
    protected String obrasocial;

    public Persona(){

    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getObrasocial() {
        return obrasocial;
    }

    public void setObrasocial(String obrasocial) {
        this.obrasocial = obrasocial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "DNI=" + DNI +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", obrasocial='" + obrasocial + '\'' +
                '}';
    }
}

package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Persona {
    int idPersona;
    String nombre;
    String apellidos;
    int edad;
    String nivelSalud;
    List<Muestra> listaMuestras;

    public Persona(){

    }

    public Persona(int idPersona, String nombre, String apellidos, int edad, String nivelSalud) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nivelSalud = nivelSalud;
        this.listaMuestras = new LinkedList<Muestra>();
    }

    public void addMuestra(Muestra nueva){
        this.listaMuestras.add(nueva);
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNivelSalud() {
        return nivelSalud;
    }

    public void setNivelSalud(String nivelSalud) {
        this.nivelSalud = nivelSalud;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", nivelSalud='" + nivelSalud + '\'' +
                ", listaMuestras=" + listaMuestras +
                '}';
    }
}

package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.Queue;

public class Laboratorio {
    int idLaboratorio;
    String nombre;
    Queue<Muestra> colaMuestras;

    public Laboratorio(){

    }
    public Laboratorio(String nombre) {
        this.nombre = nombre;
        this.colaMuestras = new LinkedList<Muestra>();      //Inicializamos la cola vacía
    }

    public Muestra procesarMuestra(){
        return this.colaMuestras.poll();
    }

    public void anadirMuestra(Muestra nueva){
        this.colaMuestras.add(nueva);
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Queue<Muestra> getColaMuestras() {
        return colaMuestras;
    }

    public void setColaMuestras(Queue<Muestra> colaMuestras) {
        this.colaMuestras = colaMuestras;
    }
}

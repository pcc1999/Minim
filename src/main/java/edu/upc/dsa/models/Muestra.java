package edu.upc.dsa.models;

public class Muestra {
    int idMuestra;
    int idClinico;
    int idPersona;
    String fechaExtraccion;
    int idLaboratorio;

    public Muestra(int idMuestra, int idClinico, int idPersona, String fechaExtraccion, int idLaboratorio) {
        this.idMuestra = idMuestra;
        this.idClinico = idClinico;
        this.idPersona = idPersona;
        this.fechaExtraccion = fechaExtraccion;
        this.idLaboratorio = idLaboratorio;
    }


    public int getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(int idMuestra) {
        this.idMuestra = idMuestra;
    }

    public int getIdClinico() {
        return idClinico;
    }

    public void setIdClinico(int idClinico) {
        this.idClinico = idClinico;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getFechaExtraccion() {
        return fechaExtraccion;
    }

    public void setFechaExtraccion(String fechaExtraccion) {
        this.fechaExtraccion = fechaExtraccion;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }
}

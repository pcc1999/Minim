package edu.upc.dsa;

import edu.upc.dsa.models.Laboratorio;
import edu.upc.dsa.models.Persona;

public interface Covid19Manager {
    public static final int maxLabs = 10;
    public void addLaboratorio(Laboratorio lab) throws LabFullException;
    public void addPersona(Persona nueva);
    public void extraerEnviarMuestra(int idPers, int idLab, String fechaExtraccion, int idClinico) throws LabNotFoundException;
    public String procesarMuestra(int idLab, String resultado, String comentario) throws LabNotFoundException;
    public void clear();
    public int numPersonas();
    public int numLabs();
    public int numMuestras();
}

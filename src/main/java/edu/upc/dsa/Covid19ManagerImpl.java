package edu.upc.dsa;

import edu.upc.dsa.models.Laboratorio;
import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Persona;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class Covid19ManagerImpl implements Covid19Manager{

    final static Logger logger = Logger.getLogger(Covid19ManagerImpl.class.getName());
    private static Covid19Manager instance;
    private int numLabs;
    private int numMuestras;
    private Laboratorio labs[];
    private HashMap<Integer, Persona> personas;

    private Covid19ManagerImpl(){
        this.personas = new HashMap<Integer, Persona>();
        numLabs = 0;
        numMuestras = 0;
        this.labs = new Laboratorio[maxLabs];
    }

    public static Covid19Manager getInstance(){
        if(instance == null){
            instance = new Covid19ManagerImpl();
        }
        return instance;
    }
    public Laboratorio getLaboratorioById(int idLab) throws LabNotFoundException {
        Laboratorio lab = null;
        for(int i = 0; i < numLabs; i++) {
            if(labs[i].getIdLaboratorio() == idLab){
                lab = labs[i];
                break;
            }
        }
        if(lab == null) {
            throw new LabNotFoundException();
        }
        else{
            return lab;
        }
    }
    @Override
    public void addLaboratorio(Laboratorio lab) throws LabFullException {
        if(numLabs == maxLabs) {
            throw new LabFullException();
        }
        else{
            lab.setIdLaboratorio(numLabs);
            labs[numLabs] = lab;
            numLabs++;
        }
    }

    @Override
    public void addPersona(Persona nueva) {
        logger.info(personas);
        logger.debug("Añadiendo persona");
        this.personas.put(nueva.getIdPersona(), nueva);
        logger.debug("Persona añadida");
        logger.info(personas);
    }

    @Override
    public void extraerEnviarMuestra(int idPers, int idLab, String fechaExtraccion, int idClinico) throws LabNotFoundException {
        logger.info(labs);
        logger.debug("Extrayendo muestra");
        Muestra muestra = new Muestra(numMuestras, idClinico, idPers, fechaExtraccion, idLab);
        numMuestras++;
        logger.debug("Muestra extraida");
        Laboratorio labProcesar = getLaboratorioById(idLab);
        labProcesar.anadirMuestra(muestra);
        logger.debug("Muestra enviada");
        logger.info(labs);
    }

    @Override
    public String procesarMuestra(int idLab, String resultado, String comentario) throws LabNotFoundException {
        logger.debug("Encontrando laboratorio");
        Laboratorio labProcesar = getLaboratorioById(idLab);
        logger.debug("Laboratorio encontrado. Procesará la primera muestra");
        logger.info(labProcesar.getColaMuestras());
        Muestra muestra = labProcesar.procesarMuestra();
        logger.info("Muestra procesada");
        logger.info("Muestra procesada" + muestra);
        logger.info(labProcesar.getColaMuestras());
        int idPers = muestra.getIdPersona();
        Persona pers = personas.get(idPers);
        pers.addMuestra(muestra);
        personas.remove(idPers);
        personas.put(idPers, pers);
        logger.debug("Generando informe segun el estado de salud de la persona");
        return "Resultado: " + resultado + ". Comentario: " + comentario;
    }

    @Override
    public void clear() {
        this.personas.clear();
        this.labs = new Laboratorio[maxLabs];
        this.numMuestras = 0;
        this.numLabs = 0;
        instance = null;
    }

    @Override
    public int numPersonas() {
        return this.personas.size();
    }

    @Override
    public int numLabs() {
        return numLabs;
    }

    @Override
    public int numMuestras() {
        return numMuestras;
    }
}

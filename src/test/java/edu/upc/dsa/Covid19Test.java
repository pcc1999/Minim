package edu.upc.dsa;

import edu.upc.dsa.models.Laboratorio;
import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Persona;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Covid19Test {
    private Covid19Manager cm;

    @Before
    public void setUp() throws Exception{
        this.cm = Covid19ManagerImpl.getInstance();

        this.cm.addPersona(new Persona(1, "Pol", "Casaña Cabrerizo", 21, "A"));

        this.cm.addLaboratorio(new Laboratorio("Lab1"));
    }
    @After
    public void tearDown(){
        this.cm.clear();
    }

    @Test
    public void testCrearUsuario() throws Exception{
        this.cm.addPersona(new Persona(2, "Alfredo", "Wenceslao", 42, "D"));
        Assert.assertEquals(2, this.cm.numPersonas());
    }

    @Test
    public void testProcesarMuestra() throws LabNotFoundException {
        this.cm.extraerEnviarMuestra(1, 0, "2020/11/23", 1);
        this.cm.procesarMuestra(0, "Negativo", "Estas hecho un toro");
        Assert.assertEquals(1, this.cm.numMuestras());
    }
}

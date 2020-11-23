package edu.upc.dsa.services;


import edu.upc.dsa.LabFullException;
import edu.upc.dsa.LabNotFoundException;
import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Laboratorio;
import edu.upc.dsa.models.Persona;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static edu.upc.dsa.Covid19ManagerImpl.getInstance;

@Api(value = "/services", description = "Endpoint to Covid-19 Management Services")
@Path("/covid19")
public class RESTService {

    @POST
    @ApiOperation(value = "create a new Persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Persona.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/addPersona/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPersona(Persona persona) {
        int idPers = persona.getIdPersona();
        String nombre = persona.getNombre();
        String apellidos = persona.getApellidos();
        String salud = persona.getNivelSalud();
        int edad = persona.getEdad();
        if(persona.getIdPersona() != 0 || persona.getNombre().equals("") || persona.getApellidos().equals("") || persona.getNivelSalud().equals("") || persona.getEdad()!=0) return Response.status(500).entity(persona).build();
        else{
            getInstance().addPersona(new Persona(idPers, nombre, apellidos, edad, salud));
        }
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "añade un nuevo laboratorio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Laboratorio.class),
            @ApiResponse(code = 500, message = "Empty Lab"),
            @ApiResponse(code = 404, message = "LabFullException")

    })

    @Path("/addLaboratorio/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoLaboratorio(Laboratorio lab) throws LabFullException {
        try {
            String nombreLab = lab.getNombre();
            if (nombreLab.equals("")) return Response.status(500).entity(lab).build();
            else {
                getInstance().addLaboratorio(new Laboratorio(nombreLab));
                return Response.status(201).entity(lab).build();
            }
        }
        catch (LabFullException e)
        {
            return Response.status(404).build();
        }
    }

    @POST
    @ApiOperation(value = "Envia una muestra a un laboratorio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Lab not found"),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/enviarMuestra")
    public Response enviarMuestra(Muestra muestra) {

        try{
            if(muestra.getFechaExtraccion().equals("")) {
                return Response.status(500).build();
            }
            else {
                getInstance().extraerEnviarMuestra(muestra.getIdPersona(), muestra.getIdLaboratorio(), muestra.getFechaExtraccion(), muestra.getIdClinico());
                return Response.status(201).build();
            }
        }
        catch (LabNotFoundException e) {
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "Recibir informe de procesado de muestra", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Lab not found")
    })
    @Path("/process/{idLab}/{resultado}/{comentario}/")
    public Response procesarMuestra(@PathParam("idLab") int idLab, @PathParam("resultado") String resultado, @PathParam("comentario") String comentario) throws LabNotFoundException {
        try {
            String res = getInstance().procesarMuestra(idLab, resultado, comentario);
            return Response.status(201).build();
        }
        catch (LabNotFoundException e)
        {
            return Response.status(404).build();
        }
    }

}

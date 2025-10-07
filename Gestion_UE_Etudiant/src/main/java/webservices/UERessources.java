package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/ue")
public class UERessources {
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();
    @Path("/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response liste()
    {
        return Response.status(200).entity(
                helper.getListeUE()
        ).build();
    }


    @Path("/ajout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
       @Produces(MediaType.TEXT_PLAIN)

    public Response ajout (UniteEnseignement ue)
    {
        if(helper.addUniteEnseignement(ue))
        {
            return Response.status(201).entity("added succesfully").build();
        }else{
            return Response.status(200).entity("added failed ").build();
        }
    }

    //delete
    @Path("/delete/{code}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code){
        if(this.helper.deleteUniteEnseignement(code)){
            return Response
                    .status(200)
                    .entity("deleted sucessfully")
                    .build();
        }return Response
                .status(404)
                .entity("not found  ")
                .build();
    }

    //search by code
    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam(value ="s") int semestre){
        return Response
                .status(200)
                .entity(this.helper.getUEBySemestre(semestre))
                .build();
    }
}

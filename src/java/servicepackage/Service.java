package servicepackage;

import java.util.List;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Service")
public class Service {
    private serverhallDBDAO d = new serverhallDBDAO();
    
    @GET
    @Path("/temp/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public List<Temperatur> getTempRapport(@PathParam("id") String id){
        return d.getTempRapport(id);
    }
    
    @GET
    @Path("/el/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public List<Electricity> getElRapport(@PathParam("id") String id){
        return d.getElRapport(id);
    }
    
    @GET
    @Path("/tempNow/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Temperatur getTemperatur(@PathParam("id") String id){
        Temperatur t = d.getTemperatur(id);
        return t;
    }
    
    @GET
    @Path("/elNow/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Electricity getElectricity(@PathParam("id") String id){
        Electricity e = d.getElectricity(id);
        return e;
    }
    
    @GET
    @Path("/maxMinAverageEl/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Electricity getMaxMinAverageEl(@PathParam("id") String id){
        Electricity e = d.getMaxMinAverageEl(id);
        return e;
    }
    
    
    @GET
    @Path("/maxMinAverageTemp/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Temperatur getMaxMinAverageTemp(@PathParam("id") String id){
        Temperatur t = d.getMaxMinAverageTemp(id);
        return t;
    }
    
    
    @GET
    @Path("/billigastDyrastTimme/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Electricity getBilligastDyrastTimme(@PathParam("id") String id){
        Electricity e = d.getBilligastDyrastTimme(id);
        return e;
    }
    
    @GET
    @Path("/elpris/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Electricity getElectricityPrice(@PathParam("id") String id){
        Electricity e = d.getElectricityPrice(id);
        return e;
    }
    
    
    @POST
    @Path("/elpris/update/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Response setNewElectricityPrice(@PathParam("id") String id, Electricity e){
        d.setNewElectricityPrice(id, e);
        Response res = new Response("Updated", Boolean.FALSE);
        res.setStatus(Boolean.TRUE);
        return res;
    }
    
    
    @POST
    @Path("/temp/update/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Response setNewTemperatur(@PathParam("id") String id, Kylsystem k){
        d.setNewTemperatur(id, k);
        Response res = new Response("Updated", Boolean.FALSE);
        res.setStatus(Boolean.TRUE);
        return res;
    }
    
    
    @POST
    @Path("/tekniker/add")
    @Produces (MediaType.APPLICATION_XML)
    public Response addTekniker(Tekniker t){
        d.addTekniker(t);
        Response res = new Response("Updated", Boolean.FALSE);
        res.setStatus(Boolean.TRUE);
        return res;
    }
}

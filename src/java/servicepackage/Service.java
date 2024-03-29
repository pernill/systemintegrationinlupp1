package servicepackage;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Service")
public class Service {
    private serverhallDBDAO d = new serverhallDBDAO();
    
    @GET
    @Path("/temp/{id}/month/{month}/day/{day}")
    @Produces (MediaType.APPLICATION_XML)
    public List<Temperatur> getTempRapport(@PathParam("id") String id,@PathParam("month") int month, @PathParam("day") int day){
        return d.getTempRapport(id,month, day);
    }
    
    @GET
    @Path("/el/{id}/month/{month}/day/{day}")
    @Produces (MediaType.APPLICATION_XML)
    public List<Electricity> getElRapport(@PathParam("id") String id,@PathParam("month") int month, @PathParam("day") int day){
        return d.getElRapport(id, month, day);
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
    @Path("/maxMinAverageEl/{id}/month/{month}/day/{day}")
    @Produces (MediaType.APPLICATION_XML)
    public Electricity getMaxMinAverageEl(@PathParam("id") String id, @PathParam("month") int month, @PathParam("day") int day){
        Electricity e = d.getMaxMinAverageEl(id, month, day);
        return e;
    }
    
    
    @GET
    @Path("/maxMinAverageTemp/{id}/month/{month}/day/{day}")
    @Produces (MediaType.APPLICATION_XML)
    public Temperatur getMaxMinAverageTemp(@PathParam("id") String id, @PathParam("month") int month, @PathParam("day") int day){
        Temperatur t = d.getMaxMinAverageTemp(id, month, day);
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
        Response res = new Response("Kw-pris uppdaterat", Boolean.FALSE);
        res.setStatus(Boolean.TRUE);
        return res;
    }
    
    
    @POST
    @Path("/temp/update/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Response setNewTemperatur(@PathParam("id") String id, Kylsystem k){
        d.setNewTemperatur(id, k);
        Response res = new Response("Temperaturinställning uppdaterad", Boolean.FALSE);
        res.setStatus(Boolean.TRUE);
        return res;
    }
    
    
    @GET
    @Path("/kylsysteminfo/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Kylsystem getKylsystemInfo(@PathParam("id") String id){
        Kylsystem k = d.getKylsystemInfo(id);
        return k;
    }
    
    
    @GET
    @Path("/highestconsumption{month}/day/{day}")
    @Produces (MediaType.APPLICATION_XML)
    public Electricity getHighestConsumption(@PathParam("month") int month, @PathParam("day") int day){
        Electricity e = d.getHighestConsumption(month, day);
        return e;
    }
    
    
    @POST
    @Path("/tekniker/add")
    @Produces (MediaType.APPLICATION_XML)
    public Response addTekniker(Tekniker t){
        d.addTekniker(t);
        Response res = new Response("Tekniker tillagd", Boolean.FALSE);
        res.setStatus(Boolean.TRUE);
        return res;
    }
}

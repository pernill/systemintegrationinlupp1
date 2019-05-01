package servicepackage;

import java.util.List;
import javax.ws.rs.GET;
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
}

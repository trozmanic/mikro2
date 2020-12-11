package si.fir.prpo.skupina57.api.v1.viri;


import si.fir.prpo.skupina57.api.v1.dtos.Kanal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("kanali")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KanaliVir {

    private Map<Kanal, Integer> kanali;

    private Logger log = Logger.getLogger(KanaliVir.class.getName());

    @PostConstruct
    private void init(){
       kanali = new HashMap<>();
       kanali.put(new Kanal("Zoom"), 1);
       kanali.put(new Kanal("Microsoft Teams"), 2);
       kanali.put(new Kanal("Discord"), 3);
       kanali.put(new Kanal("Moodle"), 4);
       kanali.put(new Kanal("BBB"), 5);
       kanali.put(new Kanal("Skype"), 6);
    }

    @GET
    public Response pridobiKanale() {
        return Response
                .ok(
                        kanali.entrySet()
                        .stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)).keySet())
                .build();
    }

    @GET
    @Path("{naziv}")
    public Response pridobiKanal(@PathParam("naziv") String naziv){

        Optional<Kanal> kanal = kanali.keySet().stream()
                .filter(key -> key.getNaziv().equals(naziv))
                .findFirst();

        if(kanal.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(kanal).build();
    }


    @POST
    public Response dodajKanal(Kanal kanal){
        if(!kanali.containsKey(kanal)){

            kanali.put(kanal, 1);

        }else{
            kanali.put(kanal, kanali.get(kanal) + 1);
        }
        log.info("Knali posodobljeni. Nova vrednost: "+ kanali.get(kanal));
        return Response.ok().build();
    }

}

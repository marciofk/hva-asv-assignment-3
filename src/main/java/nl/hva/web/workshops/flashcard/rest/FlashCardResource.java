package nl.hva.web.workshops.flashcard.rest;

import nl.hva.web.workshops.flashcard.rest.model.ClientError;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.hva.web.workshops.flashcard.model.FlashCard;
import nl.hva.web.workshops.flashcard.service.impl.RepositoryServiceImpl;
import nl.hva.web.workshops.flashcard.service.RepositoryService;


/**
 * The flash card REST resource
 * 
 * @author marciofk
 */
@Path("flashcards")
public class FlashCardResource {
    
    /** a reference to the repository service */
    private RepositoryService service;
    
    public FlashCardResource() {
        service = RepositoryServiceImpl.getInstance();
    }
    
    /**
     * Get all flash cards
     * @return a JSON representation of a list of cards
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlashCard> getAllCards() {        
        return service.getAllFlashCards();
    }
    
    /**
     * Getting a specific flash card
     * @param id
     * @return 
     */
    @GET
    @Path("/{flashCardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlashCard(@PathParam("flashCardId") int id) {
        
        FlashCard fc = service.getFlashCardFromId(id);
        
        if(fc == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ClientError("resource not found for id " + id)).build();
        } else {
            return Response.status(Response.Status.OK).entity(fc).build();
        }        
    }
    
    /**
     * Getting the question sub-resource
     * @return 
     */
    @Path("/{flashCardId}/questions")
    public QuestionResource getQuestionResource() {
        return new QuestionResource();
    }
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.web.workshops.flashcard.rest;

import nl.hva.web.workshops.flashcard.rest.model.ClientError;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.hva.web.workshops.flashcard.model.Answer;
import nl.hva.web.workshops.flashcard.model.FlashCard;
import nl.hva.web.workshops.flashcard.model.Question;
import nl.hva.web.workshops.flashcard.service.RepositoryService;
import nl.hva.web.workshops.flashcard.service.impl.RepositoryServiceImpl;

/**
 * The answer REST resource
 * 
 * Note that this is a sub-resource of question
 * 
 * @author marciofk
 */
//@Path("/")
public class AnswerResource {
    
    /** A reference to a repository service */
    private RepositoryService service;
    
    public AnswerResource() {
        service = RepositoryServiceImpl.getInstance();
    }    
    
    
    /**
     * Get all answers of a particular flash card and question
     * @param flashCardId the flash card id (path parameter of the parent resource)
     * @param questionId the flash card id (path parameter of the parent resource)
     * @param correct a query parameter indicating if the user only want correct answers
     * @return a response (could be a list of answers) or a client error
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnswers(
            @PathParam("flashCardId") int flashCardId, 
            @PathParam("questionId") int questionId,
            @DefaultValue("false") @QueryParam("correct") boolean correct) {
        
        // Getting the flash card 
        FlashCard flashCard = service.getFlashCardFromId(flashCardId);
        
        if(flashCard == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ClientError("Flash card not found for id " + flashCardId)).build();
        }    

        // Getting the question
        Question question = service.getQuestionOfFlashCard(flashCard, questionId);

        if(question == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ClientError("Question not found for id " + questionId)).build();
        }    
        
        // Retrieving the answers
        List<Answer> answers;
        if(correct) 
            answers = service.getCorrectAnswersOfQuestion(question);
        else
            answers =  service.getAllAnswersOfQuestion(question);       
        
        return Response.status(Response.Status.OK).
                    entity(answers).build();
        
    }
    
}

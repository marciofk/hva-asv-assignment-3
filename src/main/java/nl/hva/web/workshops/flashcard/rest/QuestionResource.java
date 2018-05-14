/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.web.workshops.flashcard.rest;

import nl.hva.web.workshops.flashcard.rest.model.ClientError;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.hva.web.workshops.flashcard.model.FlashCard;
import nl.hva.web.workshops.flashcard.model.Question;
import nl.hva.web.workshops.flashcard.service.RepositoryService;
import nl.hva.web.workshops.flashcard.service.impl.RepositoryServiceImpl;

/**
 *
 * The question REST sub-resource
 * 
 * @author marciofk
 */
public class QuestionResource {
    
    /** A reference to the repository service */
    private RepositoryService service;
    
    public QuestionResource() {
        service = RepositoryServiceImpl.getInstance();
    }    
    
    /**
     * Getting all questions of a flash card
     * @param flashCardId the flash card id
     * @return could be a list of questions or a client error
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllQuestions(
            @PathParam("flashCardId") int flashCardId) {
        
        // Getting flash card
        FlashCard flashCard = service.getFlashCardFromId(flashCardId);
        
        if(flashCard == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ClientError("Flash card not found for id " + flashCardId)).build();
        }
        
        // Getting questions
        List<Question> questions = service.getQuestionsOfFlashCard(flashCard);
        
        return Response.status(Response.Status.OK).
                    entity(questions).build();
    }
    
    
    /**
     * Get a specific question
     * @param flashCardId the flash card id
     * @param questionId the question id
     * @return A question representation or a client error
     */
    @GET
    @Path("/{questionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestion(
                @PathParam("flashCardId") int flashCardId,
                @PathParam("questionId") int questionId) {
                
        Response resp;
        
        // Getting the flash card
        FlashCard flashCard = service.getFlashCardFromId(flashCardId);
        
        if(flashCard == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ClientError("Flash card not found for id " + flashCardId)).build();
        }            
        
        // Getting the question
        Question question = service.getQuestionOfFlashCard(flashCard, questionId);
        
        if(question == null) {
            resp = Response.status(Response.Status.NOT_FOUND).
                    entity(new ClientError("resource not found for question id " + questionId)).build();
        } else {
            resp = Response.status(Response.Status.OK).
                    entity(question).build();
            
        }
        
        return resp;        
    }

    /**
     * Adding a question into the flashcard
     * @param flashCardId the flash card id
     * @param question the question representation
     * @return a success response or a client error
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response addQuestion(
            @PathParam("flashCardId") int flashCardId,
            Question question) {
        
        // Getting the flash card
        FlashCard card = service.getFlashCardFromId(flashCardId);
        
        if(card == null) {
            return Response.status(Response.Status.NOT_FOUND).
                        entity(new ClientError("flash card not found for id " + flashCardId)).build();
        }
        
        // Create the questing (checking duplicate id)
        boolean created = service.addQuestion(card, question);
        
        if(created) {
            return Response.status(Response.Status.CREATED).build();            
        } else {
            return Response.status(Response.Status.BAD_REQUEST).
                        entity(new ClientError("question already exists for id " + question.getId())).build();
            
        }
        
    }
    
    /**
     * Creates a answer sub-resource
     * @return 
     */
    @Path("/{questionId}/answers")
    public AnswerResource getAnswers() {
        return new AnswerResource();
    }
    
}

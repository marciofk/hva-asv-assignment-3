package nl.hva.web.workshops.flashcard.service;

import java.util.List;
import nl.hva.web.workshops.flashcard.model.Answer;
import nl.hva.web.workshops.flashcard.model.FlashCard;
import nl.hva.web.workshops.flashcard.model.Question;

/**
 * An interface containing utility methods to manage flash card data
 * @author marciofk
 */
public interface RepositoryService {
    
    /**
     * Getting all flashcards
     * @return 
     */
    List<FlashCard> getAllFlashCards();
    
    /**
     * Getting a specific flash card 
     * @param flashCardId
     * @return 
     */
    FlashCard getFlashCardFromId(int flashCardId);
    
    /**
     * Adding a flash card
     * @param card 
     */
    void addFlashCard(FlashCard card);
    
    /**
     * Adding a question into the card
     * @param card
     * @param question
     * @return 
     */
    boolean addQuestion(FlashCard card, Question question);
    
    /**
     * Getting questions from the flash card
     * @param card
     * @return 
     */
    List<Question> getQuestionsOfFlashCard(FlashCard card);
    
    /**
     * Getting a question from the flash card
     * @param flashCard
     * @param questionId
     * @return 
     */
    Question getQuestionOfFlashCard(FlashCard flashCard, int questionId);
   
    /**
     * Getting all answers of a question
     * @param question
     * @return 
     */
    List<Answer> getAllAnswersOfQuestion(Question question);    
    
    /**
     * Getting only correct answers from a question
     * @param question
     * @return 
     */
    List<Answer> getCorrectAnswersOfQuestion(Question question);
        
}

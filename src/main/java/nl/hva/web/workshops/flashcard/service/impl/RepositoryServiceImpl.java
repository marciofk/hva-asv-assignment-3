package nl.hva.web.workshops.flashcard.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import nl.hva.web.workshops.flashcard.model.Answer;
import nl.hva.web.workshops.flashcard.model.FlashCard;
import nl.hva.web.workshops.flashcard.model.Question;
import nl.hva.web.workshops.flashcard.service.RepositoryService;

/**
 *
 * A simple implementation of the repository service
 * using memory. Some predefined cards are added 
 * during class loading. 
 * 
 * @author marciofk
 */
public class RepositoryServiceImpl implements RepositoryService {

    // A singleton reference
    private static RepositoryServiceImpl instance;

    // An instance of the service is created and during class initialisation
    static {
        instance = new RepositoryServiceImpl();
        instance.loadExamples();
    }

    //  Method to get a reference to the instance (singleton)
    public static RepositoryService getInstance() {
        return instance;
    }

    // An attribute that stores all cards (in memory)
    private Map<Integer, FlashCard> elements;

    private RepositoryServiceImpl() {

        elements = new LinkedHashMap<>();
    }

    @Override
    public List<FlashCard> getAllFlashCards() {

        return new ArrayList<>(elements.values());
    }

    @Override
    public void addFlashCard(FlashCard card) {
        elements.put(card.getId(), card);
    }

    @Override
    public FlashCard getFlashCardFromId(int id) {
        return elements.get(id);
    }

    @Override
    public List<Question> getQuestionsOfFlashCard(FlashCard flashCard) {
        return flashCard.getQuestions();
    }

    @Override
    public Question getQuestionOfFlashCard(FlashCard flashCard, int questionId) {
        List<Question> questions = getQuestionsOfFlashCard(flashCard);

        if (questions == null) {
            return null;
        }

        Question found = null;
        for (Question q : questions) {
            if (q.getId() == questionId) {
                found = q;
                break;
            }
        }
        return found;
    }
    
    @Override
    public List<Answer> getCorrectAnswersOfQuestion(Question question) {
        
        return question.getAnswers().stream().filter(
                            p -> p.isCorrect() == true).
                                collect(Collectors.toList());
    }    


    @Override
    public List<Answer> getAllAnswersOfQuestion(Question q) {
                
        return q.getAnswers();
    }

    @Override
    public boolean addQuestion(FlashCard card, Question question) {
        
        return card.addQuestion(question);       
    }

    /**
     * Some dummy examples to play with
     */
    private void loadExamples() {
        FlashCard fc = new FlashCard(1, "Astronomy");
        addFlashCard(fc);
        Question q1 = new Question(1, "Earth to the moon", "What is the distance from the Earth to the Moon");
        q1.addAnswer(new Answer(1, "250000 Km", false));
        q1.addAnswer(new Answer(2, "345000 Km", false));
        q1.addAnswer(new Answer(3, "550223 Km", false));
        q1.addAnswer(new Answer(4, "384400 Km", true));

        Question q2 = new Question(2, "Speed of Light", "What is the speed of light");
        q2.addAnswer(new Answer(1, "299 782 Km/s", true));
        q2.addAnswer(new Answer(2, "250 334 Km/s", false));
        q2.addAnswer(new Answer(3, "150 331 Km/s", false));
        q2.addAnswer(new Answer(4, "900 335 Km/s", false));

        fc.addQuestion(q1);
        fc.addQuestion(q2);

        fc = new FlashCard(2, "Games");
        addFlashCard(fc);
        
        q1 = new Question(1, "Mario", "In which game, Mario, the character of Nintendo, appeared for the first time?");
        q1.addAnswer(new Answer(1, "Shinobi", false));
        q1.addAnswer(new Answer(2, "Sonic", false));
        q1.addAnswer(new Answer(3, "Donkey Kong", true));
        q1.addAnswer(new Answer(4, "Pac Man", false));

        q2 = new Question(2, "PS4", "When the famous Sony PS4 was released");
        q2.addAnswer(new Answer(1, "2013", true));
        q2.addAnswer(new Answer(2, "2011", false));
        q2.addAnswer(new Answer(3, "2010", false));
        fc.addQuestion(q1);
        fc.addQuestion(q2);
        
        fc = new FlashCard(3, "Movies");
        addFlashCard(fc);
        q1 = new Question(1, "Pioneers of Cinema", "Who invented the cinema");
        q1.addAnswer(new Answer(1, "Righteous Brothers", false));
        q1.addAnswer(new Answer(2, "Warner Brothers", false));
        q1.addAnswer(new Answer(3, "Lumiere Brothers", true));
        q1.addAnswer(new Answer(4, "Edward Klein", false));
        
        fc.addQuestion(q1);
        
    }
    

}

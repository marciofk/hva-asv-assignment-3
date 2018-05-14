package nl.hva.web.workshops.flashcard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A question of a flash card
 * 
 * @author marciofk
 */
public class Question implements Serializable {
    
    private int id;
    private String title;
    private String question;
    private List<Answer> answers;
    
    public Question() {}
    
    public Question(int id, String title, String question) {
        setId(id);
        setTitle(title);
        setQuestion(question);
        setAnswers(new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public final void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public final void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    
    public void addAnswer(Answer a) {
        getAnswers().add(a);
    }
    
}

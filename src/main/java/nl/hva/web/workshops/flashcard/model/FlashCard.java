package nl.hva.web.workshops.flashcard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A flash card representation containing a set of questions
 * 
 * @author marciofk
 */
public class FlashCard implements Serializable {
    
    private int id;
    private String name;   
    private List<Question> questions;
    
    public FlashCard() {}
    
    public FlashCard(int id, String name) {
        setId(id);
        setName(name);
        setQuestions(new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public final void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public boolean addQuestion(Question q) {
        if(checkDuplicates(q)) {
            return false;
        }
        getQuestions().add(q);
        return true;
    }
    
    public boolean checkDuplicates(Question q) {
        for(Question check : getQuestions()) {
            if(check.getId() == q.getId()) {
                return true;
            }
        }
        return false;
    }
    
}

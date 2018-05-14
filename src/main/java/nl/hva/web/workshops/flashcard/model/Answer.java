package nl.hva.web.workshops.flashcard.model;

import java.io.Serializable;

/**
 * A class representing an answer
 * @author marciofk
 */
public class Answer implements Serializable {
    
    private int id;
    private String text;
    private boolean correct;
    
    public Answer() {}
    
    public Answer(int id, String text, boolean correct) {
        setId(id);
        setText(text);
        setCorrect(correct);
    }

    public final void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public final void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public final void setCorrect(boolean correct) {
        this.correct = correct;
    }
}

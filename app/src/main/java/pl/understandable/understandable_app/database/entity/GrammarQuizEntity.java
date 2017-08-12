package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarQuizEntity extends BaseEntity {

    private String question;
    private String correct;
    private String[] incorrect = new String[3];

    public GrammarQuizEntity(int id, String question, String correctAnswer, String[] incorrectAnswers) {
        this.id = id;
        this.question = question;
        this.correct = correctAnswer;
        this.incorrect = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correct;
    }

    public String[] getIncorrectAnswers() {
        return incorrect;
    }

}

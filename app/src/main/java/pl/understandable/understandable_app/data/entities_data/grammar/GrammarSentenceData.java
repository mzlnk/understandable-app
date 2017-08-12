package pl.understandable.understandable_app.data.entities_data.grammar;

import pl.understandable.understandable_app.data.params.GrammarDataParams;
import pl.understandable.understandable_app.database.entity.GrammarFillGapEntity;
import pl.understandable.understandable_app.database.entity.GrammarSentenceEntity;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarSentenceData extends GrammarBaseData<GrammarSentenceEntity> {

    private static GrammarSentenceData grammarSentenceData;

    public static GrammarSentenceData getGrammarSentenceData() {
        return grammarSentenceData;
    }

    public static void createSentenceDataFromParams(GrammarDataParams params) {
        grammarSentenceData = new GrammarSentenceData(params);
    }

    public GrammarSentenceEntity currentWord;
    public int currentWordPosition = 0;
    public int wordsSeen = 1;
    public int correctAnswers = 0;
    public int incorrectAnswers = 0;

    public GrammarSentenceData(GrammarDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addToIncorrectAnswers();
    }

    public void addCurrentWordToSeen() {
        wordsSeen++;
    }

    public void addToCorrectAnswers() {
        correctAnswers++;
        incorrectAnswers--;
    }

    public void addToIncorrectAnswers() {
        incorrectAnswers++;
    }

    public void setCurrentWord(GrammarSentenceEntity word) {
        currentWord = word;
    }

    public void setCurrentWordPosition(int position) {
        currentWordPosition = position;
    }

    public void resetStats() {
        grammarSentenceData = new GrammarSentenceData(params);
    }

}

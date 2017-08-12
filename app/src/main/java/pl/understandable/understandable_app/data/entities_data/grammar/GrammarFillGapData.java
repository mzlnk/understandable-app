package pl.understandable.understandable_app.data.entities_data.grammar;

import pl.understandable.understandable_app.data.params.GrammarDataParams;
import pl.understandable.understandable_app.database.entity.GrammarFillGapEntity;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarFillGapData extends GrammarBaseData<GrammarFillGapEntity> {

    private static GrammarFillGapData grammarFillGapData;

    public static GrammarFillGapData getGrammarFillGapData() {
        return grammarFillGapData;
    }

    public static void createRepetitionDataFromParams(GrammarDataParams params) {
        grammarFillGapData = new GrammarFillGapData(params);
    }

    public GrammarFillGapEntity currentWord;
    public int currentWordPosition = 0;
    public int wordsSeen = 1;
    public int correctAnswers = 0;
    public int incorrectAnswers = 0;

    public GrammarFillGapData(GrammarDataParams params) {
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

    public void setCurrentWord(GrammarFillGapEntity word) {
        currentWord = word;
    }

    public void setCurrentWordPosition(int position) {
        currentWordPosition = position;
    }

    public void resetStats() {
        grammarFillGapData = new GrammarFillGapData(params);
    }

}

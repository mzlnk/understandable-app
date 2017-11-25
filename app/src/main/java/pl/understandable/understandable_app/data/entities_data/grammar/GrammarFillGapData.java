package pl.understandable.understandable_app.data.entities_data.grammar;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.params.GrammarDataParams;
import pl.understandable.understandable_app.database.entity.GrammarFillGapEntity;

/**
 * Created by Marcin Zielonka on 2017-08-12.
 */

public class GrammarFillGapData extends GrammarBaseData<GrammarFillGapEntity> {

    private static GrammarFillGapData fillGapData;

    public static GrammarFillGapData getFillGapData() {
        return fillGapData;
    }

    public static void createRepetitionDataFromParams(GrammarDataParams params) {
        DataUtil.clearAllData();
        fillGapData = new GrammarFillGapData(params);
    }

    public static void clearData() {
        fillGapData = null;
    }

    public List<GrammarFillGapEntity> wordsSeen = new ArrayList<>();
    public List<GrammarFillGapEntity> correctAnswers = new ArrayList<>();
    public List<GrammarFillGapEntity> incorrectAnswers = new ArrayList<>();

    public GrammarFillGapEntity currentWord;
    public int currentWordPosition = 0;

    public GrammarFillGapData(GrammarDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addToIncorrectAnswers();
    }

    public void addCurrentWordToSeen() {
        if(!wordsSeen.contains(currentWord)) {
            wordsSeen.add(currentWord);
        }
    }

    public void addToCorrectAnswers() {
        if(!correctAnswers.contains(currentWord)) {
            correctAnswers.add(currentWord);
        }
        if(incorrectAnswers.contains(currentWord)) {
            incorrectAnswers.remove(currentWord);
        }
    }

    public void addToIncorrectAnswers() {
        if(!incorrectAnswers.contains(currentWord)) {
            incorrectAnswers.add(currentWord);
        }
    }

    public void setCurrentWord(GrammarFillGapEntity word) {
        currentWord = word;
    }

    public void setCurrentWordPosition(int position) {
        currentWordPosition = position;
    }

    public void resetStats() {
        fillGapData = new GrammarFillGapData(params);
    }

}

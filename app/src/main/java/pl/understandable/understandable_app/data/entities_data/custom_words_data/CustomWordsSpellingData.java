package pl.understandable.understandable_app.data.entities_data.custom_words_data;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.data.entities_data.CurrentWordData;
import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;
import pl.understandable.understandable_app.database.entity.CustomWordEntity;

/**
 * Created by Marcin Zielonka on 2017-08-07.
 */

public class CustomWordsSpellingData extends CustomWordsBaseData implements CurrentWordData<CustomWordEntity> {

    private static CustomWordsSpellingData spellingData;

    public static CustomWordsSpellingData getSpellingData() {
        return spellingData;
    }

    public static void createSpellingDataFromParams(CustomWordsDataParams params) {
        DataUtil.clearAllData();
        spellingData = new CustomWordsSpellingData(params);
    }

    public static void clearData() {
        spellingData = null;
    }

    public List<CustomWordEntity> wordsSeen = new ArrayList<>();
    public List<CustomWordEntity> correctAnswers = new ArrayList<>();
    public List<CustomWordEntity> incorrectAnswers = new ArrayList<>();

    public CustomWordEntity currentWord;
    public int currentWordPosition = 0;

    public CustomWordsSpellingData(CustomWordsDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addCurrentWordToSeen();
        addToIncorrectAnswers();
    }

    @Override
    public CustomWordEntity getCurrentWord() {
        return currentWord;
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

    public void setCurrentWord(CustomWordEntity word) {
        currentWord = word;
    }

    public void setCurrentWordPosition(int position) {
        currentWordPosition = position;
    }

    public void resetStats() {
        spellingData = new CustomWordsSpellingData(params);
    }

}

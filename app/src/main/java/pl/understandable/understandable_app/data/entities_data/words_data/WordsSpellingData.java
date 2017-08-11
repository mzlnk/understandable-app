package pl.understandable.understandable_app.data.entities_data.words_data;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.database.entity.WordEntity;

/**
 * Created by Marcin on 2017-08-06.
 */

public class WordsSpellingData extends WordsBaseData {

    private static WordsSpellingData spellingData;

    public static WordsSpellingData getSpellingData() {
        return spellingData;
    }

    public static void createRepetitionDataFromParams(WordsDataParams params) {
        spellingData = new WordsSpellingData(params);
    }

    public List<WordEntity> correctAnswers = new ArrayList<>();
    public List<WordEntity> incorrectAnswers = new ArrayList<>();

    public WordEntity currentWord;
    public int currentWordPosition = 0;
    public int wordsSeen = 1;

    public WordsSpellingData(WordsDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addToIncorrectAnswers();
    }

    public void addCurrentWordToSeen() {
        wordsSeen++;
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

    public void setCurrentWord(WordEntity word) {
        currentWord = word;
    }

    public void setCurrentWordPosition(int position) {
        currentWordPosition = position;
    }

    public void resetStats() {
        spellingData = new WordsSpellingData(params);
    }

}

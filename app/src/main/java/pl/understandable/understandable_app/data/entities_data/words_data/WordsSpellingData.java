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

    public List<WordEntity> wordsToRepeat = new ArrayList<>();

    public WordEntity currentWord;

    public WordsSpellingData(WordsDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
    }

    public boolean existsInToRepeatWords(WordEntity word) {
        return wordsToRepeat.contains(word);
    }

    public void addCurrentWordToRepeat() {
        if(!wordsToRepeat.contains(currentWord)) {
            wordsToRepeat.add(currentWord);
        }
    }

    public void removeCurrentWordFromRepeat() {
        wordsToRepeat.remove(currentWord);
    }

    public void setCurrentWord(WordEntity word) {
        currentWord = word;
    }

    public WordsRepetitionData resetStats() {
        return new WordsRepetitionData(params);
    }

}

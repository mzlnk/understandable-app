package net.heliantum.understandable.data.entities_data.words_data;

import net.heliantum.understandable.data.params.WordsDataParams;
import net.heliantum.understandable.database.entity.WordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class WordsRepetitionData extends WordsBaseData {

    private static WordsRepetitionData repetitionData;

    public static WordsRepetitionData getRepetitionData() {
        return repetitionData;
    }

    public static void createRepetitionDataFromParams(WordsDataParams params) {
        repetitionData = new WordsRepetitionData(params);
    }

    public List<WordEntity> wordsToRepeat = new ArrayList<>();
    public List<WordEntity> wordsSeen = new ArrayList<>();
    public WordEntity currentWord;

    public WordsRepetitionData(WordsDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addCurrentWordToSeen();
    }

    public boolean existsInToRepeatWords(WordEntity word) {
        return wordsToRepeat.contains(word);
    }

    public void addCurrentWordToRepeat() {
        if(!wordsToRepeat.contains(currentWord)) {
            wordsToRepeat.add(currentWord);
        }
    }

    public void addCurrentWordToSeen() {
        if(!wordsSeen.contains(currentWord)) {
            wordsSeen.add(currentWord);
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

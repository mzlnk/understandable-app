package pl.understandable.understandable_app.data.entities_data.words_data;

import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.entities_data.RepetitionData;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.database.entity.WordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public class WordsRepetitionData extends WordsBaseData implements RepetitionData<WordEntity> {

    private static WordsRepetitionData repetitionData;

    public static WordsRepetitionData getRepetitionData() {
        return repetitionData;
    }

    public static void createRepetitionDataFromParams(WordsDataParams params) {
        DataUtil.clearAllData();
        repetitionData = new WordsRepetitionData(params);
    }

    public static void clearData() {
        repetitionData = null;
    }

    public List<WordEntity> wordsToRepeat = new ArrayList<>();
    public List<WordEntity> wordsSeen = new ArrayList<>();
    public WordEntity currentWord;

    public WordsRepetitionData(WordsDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addCurrentWordToSeen();
    }

    @Override
    public WordEntity getCurrentWord() {
        return currentWord;
    }

    @Override
    public List<WordEntity> getWordsToRepeat() {
        return wordsToRepeat;
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

    public void resetStats() {
       repetitionData = new WordsRepetitionData(params);
    }

}

package pl.understandable.understandable_app.data.entities_data.custom_words_data;

import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.entities_data.RepetitionData;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;
import pl.understandable.understandable_app.database.entity.CustomWordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public class CustomWordsRepetitionData extends CustomWordsBaseData implements RepetitionData<CustomWordEntity> {

    private static CustomWordsRepetitionData repetitionData;

    public static CustomWordsRepetitionData getRepetitionData() {
        return repetitionData;
    }

    public static void createRepetitionDataFromParams(CustomWordsDataParams params) {
        DataUtil.clearAllData();
        repetitionData = new CustomWordsRepetitionData(params);
    }

    public static void clearData() {
        repetitionData = null;
    }

    public List<CustomWordEntity> wordsToRepeat = new ArrayList<>();
    public List<CustomWordEntity> wordsSeen = new ArrayList<>();
    public CustomWordEntity currentWord;

    public CustomWordsRepetitionData(CustomWordsDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addCurrentWordToSeen();
    }

    @Override
    public CustomWordEntity getCurrentWord() {
        return currentWord;
    }

    @Override
    public List<CustomWordEntity> getWordsToRepeat() {
        return wordsToRepeat;
    }

    public boolean existsInToRepeatWords(CustomWordEntity word) {
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

    public void setCurrentWord(CustomWordEntity word) {
        currentWord = word;
    }

    public void resetStats() {
        repetitionData = new CustomWordsRepetitionData(params);
    }

}

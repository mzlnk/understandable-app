package net.heliantum.ziedic.data;

import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class RepetitionData extends BaseData {

    private static RepetitionData repetitionData;

    public static RepetitionData getRepetitionData() {
        return repetitionData;
    }

    public static void createRepetitionDataFromParams(DataParams params) {
        repetitionData = new RepetitionData(params);
    }

    public List<LanguageEntity> wordsToRepeat = new ArrayList<>();
    public List<LanguageEntity> wordsSeen = new ArrayList<>();
    public LanguageEntity currentWord;

    public RepetitionData(DataParams params) {
        super(params);
    }

    public boolean existsInToRepeatWords(LanguageEntity word) {
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

    public void setCurrentWord(LanguageEntity word) {
        currentWord = word;
    }

    public RepetitionData resetStats() {
       return new RepetitionData(params);
    }

}

package pl.understandable.understandable_app.data.entities_data.phrases;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.database.entity.PhraseEntity;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhrasesRepetitionData extends PhrasesBaseData {

    private static PhrasesRepetitionData repetitionData;

    public static PhrasesRepetitionData getRepetitionData() {
        return repetitionData;
    }

    public static void createRepetitionDataFromParams(PhrasesDataParams params) {
        repetitionData = new PhrasesRepetitionData(params);
    }

    public List<PhraseEntity> wordsToRepeat = new ArrayList<>();
    public List<PhraseEntity> wordsSeen = new ArrayList<>();
    public PhraseEntity currentWord;

    public PhrasesRepetitionData(PhrasesDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addCurrentWordToSeen();
    }

    public boolean existsInToRepeatWords(PhraseEntity word) {
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

    public void setCurrentWord(PhraseEntity word) {
        currentWord = word;
    }

    public void resetStats() {
        repetitionData = new PhrasesRepetitionData(params);
    }

}

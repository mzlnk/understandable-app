package pl.understandable.understandable_app.data.entities_data.irregular_verbs_data;

import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_app.database.entity.IrregularVerbEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-07-08.
 */

public class IrregularVerbsRepetitionData extends IrregularVerbsBaseData {

    private static IrregularVerbsRepetitionData repetitionData;

    public static IrregularVerbsRepetitionData getRepetitionData() {
        return repetitionData;
    }

    public static void createRepetitionDataFromParams(IrregularVerbsDataParams params) {
        DataUtil.clearAllData();
        repetitionData = new IrregularVerbsRepetitionData(params);
    }

    public static void clearData() {
        repetitionData = null;
    }

    public List<IrregularVerbEntity> wordsToRepeat = new ArrayList<>();
    public List<IrregularVerbEntity> wordsSeen = new ArrayList<>();
    public IrregularVerbEntity currentWord;

    public IrregularVerbsRepetitionData(IrregularVerbsDataParams params) {
        super(params);
        setCurrentWord(words.get(0));
        addCurrentWordToSeen();
    }

    public boolean existsInToRepeatWords(IrregularVerbEntity word) {
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

    public void setCurrentWord(IrregularVerbEntity word) {
        currentWord = word;
    }

    public void resetStats() {
        repetitionData = new IrregularVerbsRepetitionData(params);
    }

}

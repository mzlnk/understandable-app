package pl.understandable.understandable_dev_app.data.entities_data;

import pl.understandable.understandable_dev_app.data.entities_data.custom_words_data.CustomWordsListData;
import pl.understandable.understandable_dev_app.data.entities_data.custom_words_data.CustomWordsQuizData;
import pl.understandable.understandable_dev_app.data.entities_data.custom_words_data.CustomWordsRepetitionData;
import pl.understandable.understandable_dev_app.data.entities_data.custom_words_data.CustomWordsSpellingData;
import pl.understandable.understandable_dev_app.data.entities_data.grammar.GrammarFillGapData;
import pl.understandable.understandable_dev_app.data.entities_data.grammar.GrammarQuizData;
import pl.understandable.understandable_dev_app.data.entities_data.irregular_verbs_data.IrregularVerbsListData;
import pl.understandable.understandable_dev_app.data.entities_data.irregular_verbs_data.IrregularVerbsRepetitionData;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsListData;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsQuizData;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsRepetitionData;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsSpellingData;

/**
 * Created by Marcin Zielonka on 2017-08-12.
 */

public class DataUtil {

    public static void clearAllData() {
        WordsListData.clearData();
        WordsQuizData.clearData();
        WordsRepetitionData.clearData();
        WordsSpellingData.clearData();
        CustomWordsRepetitionData.clearData();
        CustomWordsQuizData.clearData();
        CustomWordsListData.clearData();
        CustomWordsSpellingData.clearData();
        IrregularVerbsListData.clearData();
        IrregularVerbsRepetitionData.clearData();
        GrammarFillGapData.clearData();
        GrammarQuizData.clearData();
    }

}

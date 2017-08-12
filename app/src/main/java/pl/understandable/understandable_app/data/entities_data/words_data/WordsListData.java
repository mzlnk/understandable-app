package pl.understandable.understandable_app.data.entities_data.words_data;

import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.params.WordsDataParams;

/**
 * Created by Marcin on 2017-05-07.
 */

public class WordsListData extends WordsBaseData {

    private static WordsListData listData;

    public static WordsListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(WordsDataParams params) {
        DataUtil.clearAllData();
        listData = new WordsListData(params);
    }

    public static void clearData() {
        listData = null;
    }

    public WordsListData(WordsDataParams params) {
        super(params);
    }

}

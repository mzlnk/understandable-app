package net.heliantum.understandable.data.entities_data.words_data;

import net.heliantum.understandable.data.params.WordsDataParams;

/**
 * Created by Marcin on 2017-05-07.
 */

public class WordsListData extends WordsBaseData {

    private static WordsListData listData;

    public static WordsListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(WordsDataParams params) {
        listData = new WordsListData(params);
    }

    public WordsListData(WordsDataParams params) {
        super(params);
    }

}

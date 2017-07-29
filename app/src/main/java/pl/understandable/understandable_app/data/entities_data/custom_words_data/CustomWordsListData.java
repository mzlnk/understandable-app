package pl.understandable.understandable_app.data.entities_data.custom_words_data;

import pl.understandable.understandable_app.data.params.CustomWordsDataParams;

/**
 * Created by Marcin on 2017-07-29.
 */

public class CustomWordsListData extends CustomWordsBaseData {

    private static CustomWordsListData listData;

    public static CustomWordsListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(CustomWordsDataParams params) {
        listData = new CustomWordsListData(params);
    }

    public CustomWordsListData(CustomWordsDataParams params) {
        super(params);
    }

}

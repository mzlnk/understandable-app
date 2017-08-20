package pl.understandable.understandable_dev_app.data.entities_data.custom_words_data;

import pl.understandable.understandable_dev_app.data.entities_data.DataUtil;
import pl.understandable.understandable_dev_app.data.params.CustomWordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public class CustomWordsListData extends CustomWordsBaseData {

    private static CustomWordsListData listData;

    public static CustomWordsListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(CustomWordsDataParams params) {
        DataUtil.clearAllData();
        listData = new CustomWordsListData(params);
    }

    public static void clearData() {
        listData = null;
    }

    public CustomWordsListData(CustomWordsDataParams params) {
        super(params);
    }

}

package pl.understandable.understandable_app.data.entities_data.phrases;

import pl.understandable.understandable_app.data.params.PhrasesDataParams;

/**
 * Created by Marcin on 2017-08-11.
 */

public class PhrasesListData extends PhrasesBaseData {

    private static PhrasesListData listData;

    public static PhrasesListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(PhrasesDataParams params) {
        listData = new PhrasesListData(params);
    }

    public PhrasesListData(PhrasesDataParams params) {
        super(params);
    }

}

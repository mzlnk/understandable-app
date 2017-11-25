package pl.understandable.understandable_app.data.entities_data.irregular_verbs_data;

import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;

/**
 * Created by Marcin Zielonka on 2017-07-08.
 */

public class IrregularVerbsListData extends IrregularVerbsBaseData {

    private static IrregularVerbsListData listData;

    public static IrregularVerbsListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(IrregularVerbsDataParams params) {
        DataUtil.clearAllData();
        listData = new IrregularVerbsListData(params);
    }

    public static void clearData() {
        listData = null;
    }

    public IrregularVerbsListData(IrregularVerbsDataParams params) {
        super(params);
    }

}

package net.heliantum.understandable.data.irregular_verbs_data;

import net.heliantum.understandable.data.params.IrregularVerbsDataParams;

/**
 * Created by Marcin on 2017-07-08.
 */

public class IrregularVerbsListData extends IrregularVerbsBaseData {

    private static IrregularVerbsListData listData;

    public static IrregularVerbsListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(IrregularVerbsDataParams params) {
        listData = new IrregularVerbsListData(params);
    }

    public IrregularVerbsListData(IrregularVerbsDataParams params) {
        super(params);
    }

}

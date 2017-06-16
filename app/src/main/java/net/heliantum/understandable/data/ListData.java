package net.heliantum.understandable.data;

/**
 * Created by Marcin on 2017-05-07.
 */

public class ListData extends BaseData {

    private static ListData listData;

    public static ListData getListData() {
        return listData;
    }

    public static void createListDataFromParams(DataParams params) {
        listData = new ListData(params);
    }

    public ListData(DataParams params) {
        super(params);
    }

}

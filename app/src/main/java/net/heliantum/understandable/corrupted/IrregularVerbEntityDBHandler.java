package net.heliantum.understandable.corrupted;

/**
 * Created by Marcin on 2017-01-08.
 */

public class IrregularVerbEntityDBHandler extends BaseDBHandler {

    /*todo: remove it
    public IrregularVerbEntityDBHandler(Context context) {
        super(context);
    }

    public List<IrregularVerbEntity> getData() {
        Cursor c;
        //table 'irregular_verbs':
        c = db.rawQuery("SELECT * FROM 'irregular_verbs'", null);

        List<IrregularVerbEntity> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                result.add(new IrregularVerbEntity(c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3), c.getString(4), c.getString(5)));
            }while(c.moveToNext());
        }
        return result;
    }
    */

}

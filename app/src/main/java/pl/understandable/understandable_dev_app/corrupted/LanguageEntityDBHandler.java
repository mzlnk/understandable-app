package pl.understandable.understandable_dev_app.corrupted;

/**
 * Created by Marcin on 2017-01-08.
 */

public class LanguageEntityDBHandler extends BaseDBHandler {

    /*todo: remove it
    public LanguageEntityDBHandler(Context context) {
        super(context);
    }

    public List<WordEntity> getData() {
        Cursor c;
        //table 'words':
        c = db.rawQuery("SELECT * FROM 'words'", null);

        List<WordEntity> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                result.add(new WordEntity(c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        LanguageCategory.getEnum(c.getString(3)),
                        LanguageType.getEnum(c.getString(4))));
            }while(c.moveToNext());
        }
        return result;
    }

    public void updateData() {

        //todo: remove in beta version (it'll have been replaced by MYSQL on VPS)

        Log.d(DEBUG_TAG, "Starting updating local database...");

        Scanner sc;
        List<String> statements = new ArrayList<>();
        InputStream is = null;

        try {
            is = context.getAssets().open("sql_statements/sql_temp_statements.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc = new Scanner(is);

        while(sc.hasNextLine()) {
            statements.add(sc.nextLine());
        }

        for(String statement : statements) {
            Log.d(DEBUG_TAG, "Executing statement: " + statement);
            db.execSQL(statement);
        }

        Log.d(DEBUG_TAG, "Executing statements has finished");
    }
    */

}

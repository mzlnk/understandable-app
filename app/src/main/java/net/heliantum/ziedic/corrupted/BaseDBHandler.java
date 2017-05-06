package net.heliantum.ziedic.corrupted;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class BaseDBHandler {

    /*todo: remove it
    protected static final String DEBUG_TAG = "ZiedicDatabase";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ziedic.db";

    protected Context context;
    protected static SQLiteDatabase db;
    private static DBHandler dbHandler;

    private static class DBHandler extends SQLiteAssetHelper {

        private Context context;

        public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.context = context;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d(DEBUG_TAG, "Upgrading database from version: " + oldVersion + "to: " + newVersion);

            int oldV = oldVersion;
            int newV = newVersion;

            do {

                try {

                    InputStream is = context.getAssets().open("sql_statements/sql_onupgrade/" + (oldV + 1) + ".txt");
                    Scanner scanner = new Scanner(is);
                    String statement;

                    Log.d(DEBUG_TAG, "Upgrading database to version: " + (oldV + 1));

                    while(scanner.hasNextLine()) {
                        statement = scanner.nextLine();
                        Log.d(DEBUG_TAG, "Executing statement: " + statement);
                        db.execSQL(statement);
                    }

                    scanner.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                oldV++;

            } while (oldV == newV);

            Log.d(DEBUG_TAG, "Database has been upgraded to version: " + newVersion);
        }

        List<String> getStatements() {

            Scanner sc;
            List<String> result = new ArrayList<>();

            InputStream is = null;

            try {
                is = context.getAssets().open("sql_statements/sql_oncreate.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            sc = new Scanner(is);

            while(sc.hasNextLine()) {
                result.add(sc.nextLine());
            }

            return result;
        }

    }

    public BaseDBHandler(Context context) {
        this.context = context;
    }

    public BaseDBHandler open() {

        dbHandler = new DBHandler(context, DATABASE_NAME, null, DATABASE_VERSION);

        try {
            db = dbHandler.getWritableDatabase();
        }catch(SQLException e) {
            db = dbHandler.getReadableDatabase();
        }

        return this;
    }

    public void close() {
        dbHandler.close();
    }
    */

}

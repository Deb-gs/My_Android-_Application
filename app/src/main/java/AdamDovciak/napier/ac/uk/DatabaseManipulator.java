package AdamDovciak.napier.ac.uk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManipulator {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "newtable";
    private static Context context;
    static SQLiteDatabase db;
    private SQLiteStatement insertStmt;

    private static final String INSERT = "insert into " + TABLE_NAME
            + " (playerName1,playerName2,playerScore1, playerScore2) values (?,?,?,?)";

    public DatabaseManipulator(Context context) {
        DatabaseManipulator.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        DatabaseManipulator.db = openHelper.getWritableDatabase();
        this.insertStmt = DatabaseManipulator.db.compileStatement(INSERT);
    }

    public long insert(String playerName1, String PlayerName2, String playerScore1, String playerScore2){
        this.insertStmt.bindString(1, playerName1);
        this.insertStmt.bindString(3, PlayerName2);
        this.insertStmt.bindString(2, playerScore1);
        this.insertStmt.bindString(4, playerScore2);
        return this.insertStmt.executeInsert();
    }

    public void deleteAll() {
        db.delete(TABLE_NAME, null, null);
    }

    public List<String[]> selectAll() {
        List<String[]> list = new ArrayList<String[]>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id", "playerName1", "PlayerName2", "playerScore1", "PlayerScore2",}, null, null, null, null, "playerName1 asc");
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                String[] b1 = new String[]{cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4)};
                list.add(b1);
                x++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return list;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "
                    + TABLE_NAME
                    + " (id INTEGER PRIMARY KEY, playerName1 TEXT, PlayerName2 TEXT, playerScore1 TEXT, playerScore2 TEXT)");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            DATABASE_VERSION = newVersion;
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}

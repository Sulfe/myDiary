package ac.baekseok.diary2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    static final String DbName = "myNote.db";
    static int DbVersion = 1;
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(@Nullable Context context) {
        super(context, DbName, null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE noteTbl(noteId INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, picture TEXT, date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE noteTbl";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }


}
package ac.baekseok.diary2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "DiaryDB.db";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE "+ TableInfo.TABLE_NAME +"(" +
                    TableInfo.COLUMN_NAME_ID+ "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                    TableInfo.COLUMN_NAME_TITLE+ "TEXT ," +
                    TableInfo.COLUMN_NAME_CONTENT+ "TEXT ," +
                    TableInfo.COLUMN_NAME_PICTURE+ "TEXT ,"+
                    TableInfo.COLUMN_NAME_DATE+ "TIMESTAMP" + ")";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME;

    public NoteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}


package ac.baekseok.diary2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mainCal, mainSet, mainWrite, mainSearch;
    RecyclerView mainRecycle;
    ArrayList<Note> notes = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    @SuppressLint("Range")

    public ArrayList<Note>selectALL(){
        ArrayList<Note>result=new ArrayList<Note>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM noteTbl",null);
        for (int i =0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            int noteID = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String picture = cursor.getString(3);
            String date = cursor.getString(4);

            Note n = new Note(noteID,title,content,picture,date);
            result.add(n);
        }
        return result;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecycle = (RecyclerView) findViewById(R.id.recyclerView);
        mainCal=(Button) findViewById(R.id.mainCal);
        mainSet=(Button) findViewById(R.id.mainSet);
        mainSearch=(Button) findViewById(R.id.mainSearch);
        mainWrite=(Button) findViewById(R.id.mainWrite);




       //db생성
        DBHelper dbHelper = new DBHelper(this);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        notes = selectALL();

        mainWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                finish();
                startActivity(intent);
            }
        });//mainWrite

        mainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });//mainSearch


        NoteAdapter noteAdapter = new NoteAdapter(this,notes);
        mainRecycle.setLayoutManager(new LinearLayoutManager(this));
        mainRecycle.setAdapter(noteAdapter);
        noteAdapter.notifyDataSetChanged();




    }//onCreate()
}//MainActivity
package ac.baekseok.diary2;

import android.content.Context;
import android.content.Intent;
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
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecycle = (RecyclerView) findViewById(R.id.recyclerView);
        mainCal=(Button) findViewById(R.id.mainCal);
        mainSet=(Button) findViewById(R.id.mainSet);
        mainSearch=(Button) findViewById(R.id.mainSearch);
        mainWrite=(Button) findViewById(R.id.mainWrite);

       NoteAdapter noteAdapter = new NoteAdapter();


        mainWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });//mainWrite

        mainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });//mainSearch

        mainRecycle.setAdapter(noteAdapter);
        mainRecycle.setLayoutManager(new LinearLayoutManager(this));

        notes = new ArrayList<>();
        for(int i=1;i<=10;i++){
            if(i%2==0)
                notes.add(new Note(i+"번째 제목",i+"번째 내용",i+"번째 날짜"));
            else
                notes.add(new Note(i+"번째 제목",i+"번째 내용",i+"번째 날짜"));

        }
        noteAdapter.setNotes(notes);

    }//onCreate()
}//MainActivity
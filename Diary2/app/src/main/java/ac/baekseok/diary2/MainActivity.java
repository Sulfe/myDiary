package ac.baekseok.diary2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    NoteAdapter noteAdapter;
    FloatingActionButton mainWrite;
    RecyclerView mainRecycle;
    Toolbar mainToolbar;
    ArrayList<Note> notes = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    private final long finishTime = 2000;
    private long backPressedTime = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        mainWrite = (FloatingActionButton) findViewById(R.id.mainWrite);

        //toolbar
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("");

        //db생성
        DBHelper dbHelper = new DBHelper(this);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        notes = selectALL();

        mainWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });//mainWrite

        setUpRecycleView(notes);

    }//onCreate()


    private void setUpRecycleView(ArrayList<Note> item) {

        mainRecycle = (RecyclerView) findViewById(R.id.recyclerView);
        mainRecycle.setHasFixedSize(true);
        noteAdapter = new NoteAdapter(this, item);
        mainRecycle.setLayoutManager(new LinearLayoutManager(this));
        mainRecycle.setAdapter(noteAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                noteAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    public ArrayList<Note> selectALL() {
        ArrayList<Note> result = new ArrayList<Note>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM noteTbl", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            int noteID = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String picture = cursor.getString(3);
            String date = cursor.getString(4);

            Note n = new Note(noteID, title, content, picture, date);
            result.add(n);
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && finishTime >= intervalTime) {
            finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}//MainActivity

     /* noteAdapter.setOnItemLongClickListener(new NoteAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int pos) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("메모 삭제");
                builder.setMessage("메모를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        *//*int deletedCount = db.delete("noteTbl","noteID = "+pos,null);*//*
                        int deletedCount = 0;
                        if (deletedCount==0){
                            Toast.makeText(MainActivity.this,"문제가 생겨 삭제되지 않았습니다.",Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(MainActivity.this,"삭제가 되었습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("취소",null);
                builder.show();

            }
        });
*/


package ac.baekseok.diary2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    Toolbar writeToolbar;
    EditText wteTitle, wteContent;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    TextView dateText;
    long dateNow;
    Date mDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    private String getTime(){
        dateNow = System.currentTimeMillis();
        mDate = new Date(dateNow);
        return dateFormat.format(mDate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.write_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.writeSave){
            String title, content;
            title = wteTitle.getText().toString();
            content = wteContent.getText().toString();
            String query = "INSERT INTO noteTbl(title,content) VALUES('" + title + "', '" + content + "')";
            sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.execSQL(query);
            sqLiteDatabase.close();
            Toast.makeText(getApplicationContext(),"입력됨",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrtie);

        writeToolbar = (Toolbar) findViewById(R.id.writeToolbar);
        wteTitle = (EditText) findViewById(R.id.wteTitle);
        wteContent = (EditText) findViewById(R.id.wteContent);
        dateText = (TextView) findViewById(R.id.dateText);

        dateText.setText(getTime());

        String d = dateText.getText().toString();

        setSupportActionBar(writeToolbar);
        getSupportActionBar().setTitle(d);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //db생성
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();


    }
}

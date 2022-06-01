package ac.baekseok.diary2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    Button wteSave,wteBack;
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

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrtie);

        wteSave = (Button) findViewById(R.id.wteSave);
        wteTitle = (EditText) findViewById(R.id.wteTitle);
        wteContent = (EditText) findViewById(R.id.wteContent);
        wteBack = (Button) findViewById(R.id.wteBack);
        dateText = (TextView) findViewById(R.id.dateText);

        dateText.setText(getTime());


        //db생성
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        wteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, content;
                title = wteTitle.getText().toString();
                content = wteContent.getText().toString();
                String query = "INSERT INTO noteTbl(title,content) VALUES('" + title + "', '" + content + "')";
                sqLiteDatabase = dbHelper.getWritableDatabase();
                sqLiteDatabase.execSQL(query);
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(),"입력됨",Toast.LENGTH_LONG).show();

            }
        });

        wteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });




    }
}

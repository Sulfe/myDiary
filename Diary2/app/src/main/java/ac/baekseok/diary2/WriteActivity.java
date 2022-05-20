package ac.baekseok.diary2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {

    Button wteSave,wteBack;
    EditText wteTitle, wteContent;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrtie);

        NoteDatabase myDBHelper = new NoteDatabase(getApplicationContext());

        wteSave =(Button) findViewById(R.id.wteSave);
        wteBack =(Button) findViewById(R.id.wteBack);
        wteTitle = (EditText)findViewById(R.id.wteTitle);
        wteContent = (EditText)findViewById(R.id.wteContent);

        String title = wteTitle.getText().toString();
        String content = wteContent.getText().toString();

        wteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(TableInfo.COLUMN_NAME_TITLE,title);
                values.put(TableInfo.COLUMN_NAME_CONTENT,content);
                SQLiteDatabase db = myDBHelper.getWritableDatabase();
                db.insert(TableInfo.TABLE_NAME,null,values);
            }
        });
    }
}

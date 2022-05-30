package ac.baekseok.diary2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class WriteActivity extends AppCompatActivity {

    Button wteSave,wteBack;
    EditText wteTitle, wteContent;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrtie);

        wteSave = (Button) findViewById(R.id.wteSave);
        wteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, content;
                title = wteTitle.getText().toString();
                content = wteContent.getText().toString();
            }
        });

        



    }
}

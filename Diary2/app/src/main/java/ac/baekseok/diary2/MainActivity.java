package ac.baekseok.diary2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mainCal, mainSet, mainWrite, mainSearch;
    SQLiteDatabase database;
    myDBHelper myDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCal=(Button) findViewById(R.id.mainCal);
        mainSet=(Button) findViewById(R.id.mainSet);
        mainSearch=(Button) findViewById(R.id.mainSearch);
        mainWrite=(Button) findViewById(R.id.mainWrite);

        mainWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
            }
        });//mainWrite

        mainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            }
        });//mainSearch

    }//onCreate()

    public class myDBHelper extends SQLiteOpenHelper {
        //db생성 테이블 생성 클래스
        public myDBHelper(Context context){
            super(context,"diaryDB",null,1);
        }//myDBHelper()
        //onCreate() 테이블 생성, onUpgrade 테이블 삭제 다시 생성

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE diaryTBL(id Integer PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT NOT NULL,content TEXT, picture TEXT, date Timestamp NOT NULL);");//데이터베이스 명령어 처리

        }//onCreate

        //테이블이 있으면 테이블의 내용을 삭제하고 다시 생성
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//int i는 old테이블명, i1 새로 생성 테이블 명
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS diaryTBL");
            onCreate(sqLiteDatabase);
        }
    }//myDBHelper

}//MainActivity
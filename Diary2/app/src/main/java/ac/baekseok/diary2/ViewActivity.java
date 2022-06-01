package ac.baekseok.diary2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    TextView viewTitle, viewContent, viewDate;
    String title, content, mDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        viewTitle = (TextView) findViewById(R.id.viewTitle);
        viewContent = (TextView) findViewById(R.id.viewContent);
        viewDate = (TextView) findViewById(R.id.viewdate);

        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        content = intent.getExtras().getString("content");
        mDate = intent.getExtras().getString("date");


        viewTitle.setText(title);
        viewContent.setText(content);
        viewDate.setText(mDate);


    }
}

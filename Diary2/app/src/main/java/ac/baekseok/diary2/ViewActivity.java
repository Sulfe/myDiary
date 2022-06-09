package ac.baekseok.diary2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewActivity extends AppCompatActivity {
    TextView viewTitle, viewContent, viewDate;
    Toolbar viewToolbar;
    String title, content, mDate;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.viewEdit) {
            Toast.makeText(this, "수정 클릭됨", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        viewTitle = (TextView) findViewById(R.id.viewTitle);
        viewContent = (TextView) findViewById(R.id.viewContent);
        viewDate = (TextView) findViewById(R.id.viewDate);
        viewToolbar = (Toolbar) findViewById(R.id.viewToolbar);

        setSupportActionBar(viewToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        content = intent.getExtras().getString("content");
        mDate = intent.getExtras().getString("date");


        viewTitle.setText(title);
        viewContent.setText(content);
        viewDate.setText(mDate);


    }
}

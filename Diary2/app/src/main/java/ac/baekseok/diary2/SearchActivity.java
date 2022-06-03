package ac.baekseok.diary2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ListView searchList;
    ArrayList<Note> notes = new ArrayList<Note>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    private void searchNote(){
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Note> filterNote = new ArrayList<>();
                for (int i = 0; i < notes.size(); i++){
                    Note note = notes.get(i);
                    if (note.getTitle().toLowerCase().contains(newText.toLowerCase())){
                        filterNote.add(note);
                    }
                }

                return false;
            }
        });
    }
}

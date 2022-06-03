package ac.baekseok.diary2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Note> notes = new ArrayList<>();

    public SearchAdapter(Context context, ArrayList<Note> notes) {
        this.notes = notes;
        mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.contentTitle);
            content = (TextView) itemView.findViewById(R.id.contentCon);
            date = (TextView) itemView.findViewById(R.id.contentDate);

        }
    }


    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

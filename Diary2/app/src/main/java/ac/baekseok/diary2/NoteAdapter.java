package ac.baekseok.diary2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private ArrayList<Note> notes = new ArrayList<Note>();

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, content, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.contentTitle);
            content = (TextView) itemView.findViewById(R.id.contentCon);
            date = (TextView) itemView.findViewById(R.id.contentDate);
        }

        void onBind(Note item){
            title.setText(item.getTitle());
            content.setText(item.getContent());
            date.setText(item.getDate());
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(notes.get(position));
    }

    public void setNotes(ArrayList<Note> list){
        this.notes = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


}
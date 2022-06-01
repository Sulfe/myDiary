package ac.baekseok.diary2;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Note> notes = new ArrayList<>();


    public NoteAdapter(Context context,ArrayList<Note> notes) {
        this.notes = notes;
        mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, content, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.contentTitle);
            content = (TextView) itemView.findViewById(R.id.contentCon);
            date = (TextView) itemView.findViewById(R.id.contentDate);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(mContext,ViewActivity.class);
                    if(pos != RecyclerView.NO_POSITION){
                        intent.putExtra("title",notes.get(pos).getTitle());
                        intent.putExtra("content",notes.get(pos).getContent());
                        intent.putExtra("date",notes.get(pos).getDate());
                        ((MainActivity)mContext).startActivity(intent);
                    }
                }
            });
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

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void addItem(Note item) {
        notes.add(item);
    }

    public Note getItem(int pos) {
        return notes.get(pos);
    }

}
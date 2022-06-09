package ac.baekseok.diary2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FilterReader;
import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<Note> notes;
    private ArrayList<Note> notesFull;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.notes = notes;
        notesFull = new ArrayList<>(notes);
        mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
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
                    Intent intent = new Intent(mContext, ViewActivity.class);
                    if (pos != RecyclerView.NO_POSITION) {
                        intent.putExtra("title", notes.get(pos).getTitle());
                        intent.putExtra("content", notes.get(pos).getContent());
                        intent.putExtra("date", notes.get(pos).getDate());
                        ((MainActivity) mContext).startActivity(intent);
                    }
                }
            });


        }

        void onBind(Note item) {
            title.setText(item.getTitle());
            content.setText(item.getContent());
            date.setText(item.getDate());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note currentItem = notes.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.content.setText(currentItem.getContent());
        holder.date.setText(currentItem.getDate());
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

    @Override
    public Filter getFilter() {
        return notesFilter;
    }

    private Filter notesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Note> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(notesFull);

            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Note item : notesFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notes.clear();
            notes.addAll((ArrayList<Note>) filterResults.values);
            notifyDataSetChanged();
        }
    };

}

/*    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int pos);
    }

    private OnItemLongClickListener mLongListener = null;

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.mLongListener = listener;
    }*/


  /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if (mLongListener!=null){
                            mLongListener.onItemLongClick(view,pos);
                        }
                    }
                    return false;
                }
            });*/
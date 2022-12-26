package com.example.android.notes_app_mvvm.Adpter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.notes_app_mvvm.Activity.UpdateNotesActivity;
import com.example.android.notes_app_mvvm.MainActivity;
import com.example.android.notes_app_mvvm.R;
import com.example.android.notes_app_mvvm.model.notes;

import java.util.ArrayList;
import java.util.List;


public class NotesAdpter extends RecyclerView.Adapter<NotesAdpter.notesViewHolder> {
    MainActivity mainActivity;
    List<notes> notes;
    List<notes> allNotesItem;

    public NotesAdpter(MainActivity mainActivity, List<notes> notes) {
        this.mainActivity=mainActivity;
        this.notes=notes;
        allNotesItem= new ArrayList<>(notes);
    }
    public void SearchNotes(List<notes> filternotes){
        this.notes=filternotes;
        notifyDataSetChanged();

    }
    @Override
    public notesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(  NotesAdpter.notesViewHolder holder, int position) {
        switch (notes.get(position).notespriority) {
            case "1":
                holder.Priority.setBackgroundResource(R.drawable.green);

                break;
            case "2":
                holder.Priority.setBackgroundResource(R.drawable.yellow);

                break;
            case "3":
                holder.Priority.setBackgroundResource(R.drawable.red);

                break;
        }
        holder.title.setText(notes.get(position).notesTitle);
        holder.subtitle.setText(notes.get(position).notesSubtitle);
        holder.noteDate.setText(notes.get(position).notesDate);
        holder.itemView.setOnClickListener(view ->  {
            Intent i=new Intent(mainActivity, UpdateNotesActivity.class);
            i.putExtra("id",notes.get(position).id);
            i.putExtra("title",notes.get(position).notesTitle);
            i.putExtra("subtitle",notes.get(position).notesSubtitle);
            i.putExtra("Data",notes.get(position).notes);
            i.putExtra("Priority",notes.get(position).notespriority);
            mainActivity.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder{
        TextView title,subtitle,noteDate;
        View Priority;
        public notesViewHolder( View itemView) {
            super(itemView);
            Priority=itemView.findViewById(R.id.notesPriority);
            title=itemView.findViewById(R.id.notetitle);
            subtitle=itemView.findViewById(R.id.noteSubtitle);
            noteDate=itemView.findViewById(R.id.noteDate);

        }
    }
}

package com.example.csc372_mikeskwierawski_a2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<IndivObject> noteList;
    private MainActivity mainActivity;
    ListAdapter(ArrayList<IndivObject> noteList, MainActivity mainActivity){
        this.noteList = noteList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // item view is one particular instance of this
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        itemView.setOnClickListener(mainActivity);
        itemView.setOnLongClickListener(mainActivity);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IndivObject selectedNote = noteList.get(position);
        if (holder.title.length() >= 80){
            holder.title.setText(selectedNote.getTitle().substring(0,79) + " ...");
        }else{
            holder.title.setText(selectedNote.getTitle());
        }
        holder.body.setText(selectedNote.getBody());
        // format time here
        holder.time.setText(selectedNote.getTime());

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


}

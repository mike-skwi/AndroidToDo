package com.example.csc372_mikeskwierawski_a2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView body;
    TextView time;

    ViewHolder(View view){
        super(view);
        title = view.findViewById(R.id.titleTextRec);
        body = view.findViewById(R.id.bodyTextRec);
        time = view.findViewById(R.id.timeTextRec);

    }

}

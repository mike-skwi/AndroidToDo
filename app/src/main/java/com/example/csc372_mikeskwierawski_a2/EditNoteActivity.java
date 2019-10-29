package com.example.csc372_mikeskwierawski_a2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;

public class EditNoteActivity extends AppCompatActivity {

    TextView title;
    TextView body;
    String[] noteArray = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit/Create Note: ");

        title = findViewById(R.id.noteTitle);
        body = findViewById(R.id.noteBody);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveIcon:
//                Toast.makeText(this,"Save Selected",Toast.LENGTH_SHORT).show();
//                Intent intentEdit = new Intent(MainActivity.this, EditNoteActivity.class);
//                startActivity(intentEdit);
                // if title is empty:
                if(title.getText().length()==0 || title.getText()==" "){
                    Toast.makeText(this,"Please Give Your Note a Title", Toast.LENGTH_SHORT).show();
                }
                else{
                    //send intent to mainactivity
//                    Intent data = new Intent();
                    Intent data = new Intent();


                    noteArray[0] = title.getText().toString();
                    noteArray[1] = body.getText().toString();
                    noteArray[2] = "time : ) ";

                    data.putExtra("NOTE", noteArray);
                    setResult(RESULT_OK, data);
                    finish();

                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        Toast.makeText(this,"Put a confirmation message here..", Toast.LENGTH_SHORT).show();
        super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder((this));


        builder.setMessage("Do you want to leave without saving?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent emptyIntent = new Intent();
                        setResult(RESULT_CANCELED,emptyIntent);

                        dialogInterface.cancel();
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int o) {
//                alert.dismiss();
                dialogInterface.cancel();
            }
        });
                AlertDialog alert = builder.create();

                alert.show();


    }

}

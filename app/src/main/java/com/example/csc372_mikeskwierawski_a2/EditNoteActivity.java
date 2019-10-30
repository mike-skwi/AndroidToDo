package com.example.csc372_mikeskwierawski_a2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditNoteActivity extends AppCompatActivity {

    TextView title;
    TextView body;

    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = myDateObj.format(myFormatObj);


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

    @RequiresApi(api = Build.VERSION_CODES.O)
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
                    noteArray[2] = formattedDate;

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

        AlertDialog.Builder builder = new AlertDialog.Builder((EditNoteActivity.this));


        builder.setMessage("Do you want to leave without saving?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finishActivity(RESULT_CANCELED);
//                        Intent emptyIntent = new Intent(null,EditNoteActivity.class);
//                        setResult(RESULT_CANCELED,emptyIntent);
//                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int o) {
//                alert.dismiss();

                dialogInterface.dismiss();

            }
        });
//                AlertDialog alert = builder.create();
//                alert.show();

//                builder.create().show();

                builder.setCancelable(false);
                builder.show();
    }

}

package com.example.csc372_mikeskwierawski_a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    // this array list will be the json object class
//    ArrayList<String[]> listOfObjs = new ArrayList<>();
    ArrayList<IndivObject> listOfObjs = new ArrayList<>();
    int numberOfNotes;
    private static final int editRequestCode = 1;
//    public final String list = "NOTE";
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes: " + numberOfNotes);

        recyclerView = findViewById(R.id.recycler);
        listAdapter = new ListAdapter(listOfObjs,this);

        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addIcon:
//                Toast.makeText(this,"Add Selected",Toast.LENGTH_SHORT).show();
                Intent intentEdit = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivityForResult(intentEdit,editRequestCode);

                return true;
            case R.id.infoIcon:
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentAbout);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
//        Bundle extras = intent.getExtras();
//        if (intent.hasExtra("NOTE") == true) {
          if (resultCode == RESULT_OK){
//            String[] recievedArray = extras.getStringArray("NOTE");
            String[] recievedArray = intent.getStringArrayExtra("NOTE");

            IndivObject indivObject = new IndivObject(recievedArray[0],recievedArray[1],recievedArray[2]);
            listOfObjs.add(indivObject);


//            listOfObjs.add(recievedArray);

//            testArea.setText(recievedArray[0]);
//            testArea.setText(listOfObjs.get(0)[0]);
            numberOfNotes = listOfObjs.size();
            getSupportActionBar().setTitle("Notes: " + numberOfNotes);
        }
        else{
            Toast.makeText(this,"Extras null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int positionOfClicked = recyclerView.getChildLayoutPosition(view);
        IndivObject selection = listOfObjs.get(positionOfClicked);
//        listOfObjs.remove(selection);
    }

    @Override
    public boolean onLongClick(View view) {
        int positionOfClicked = recyclerView.getChildLayoutPosition(view);
        final IndivObject selection = listOfObjs.get(positionOfClicked);

        AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
        builder.setMessage("Do you want to delete this note?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listOfObjs.remove(selection);
                        listAdapter.notifyDataSetChanged();
                        numberOfNotes = listOfObjs.size();
                        getSupportActionBar().setTitle("Notes: " + numberOfNotes);
                    }
                }).setNegativeButton("No",null);

        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }

}

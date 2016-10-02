package com.example.android.todolist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Note extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText text;
    Button save;
    MainActivity p;

    Time today = new Time(Time.getCurrentTimezone());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        myDb = new DatabaseHelper(this);
        text = (EditText) findViewById(R.id.textNote);
        save = (Button) findViewById(R.id.save);
        AddData();

    }

    public void AddData(){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setToNow();
                String timestamp = today.format("%Y-%m-%d %H:%M");
                boolean isInserted = myDb.insertData(text.getText().toString(),timestamp);
                if(isInserted==true) {
                    Toast.makeText(Note.this, "Data inserted", Toast.LENGTH_LONG).show();
                    Intent n = new Intent(Note.this,MainActivity.class);
                    startActivity(n);
                }
                else
                    Toast.makeText(Note.this,"Data not inserted",Toast.LENGTH_LONG).show();

            }
        });

    }

}

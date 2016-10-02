package com.example.android.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText text;
    Button update;
    Time today = new Time(Time.getCurrentTimezone());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb = new DatabaseHelper(this);
        text = (EditText) findViewById(R.id.updateNote);
        update = (Button) findViewById(R.id.update);
        Intent mIntent = getIntent();
        final long id = mIntent.getLongExtra("id",0);
        updateData(id);
    }

    public void updateData(final long id){

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor c = myDb.row(id);
                if(c.moveToFirst()){

                    String task = text.getText().toString();
                    today.setToNow();
                    String timestamp = today.format("%Y-%m-%d %H:%M");
                    myDb.update(id,task,timestamp);
                }
                c.close();
                Intent n = new Intent(Update.this,MainActivity.class);
                startActivity(n);
//                boolean isInserted = myDb.insertData(text.getText().toString(),timestamp);
//                if(isInserted==true) {
//                    Toast.makeText(Update.this, "Data update", Toast.LENGTH_LONG).show();
//                    Intent n = new Intent(Update.this,MainActivity.class);
//                    startActivity(n);
//                }
//                else
//                    Toast.makeText(Update.this,"Data not updated",Toast.LENGTH_LONG).show();

            }
        });

    }
}

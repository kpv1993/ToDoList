package com.example.android.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    Update upd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newNote = (Button) findViewById(R.id.newNote);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent n = new Intent(MainActivity.this,Note.class);
                startActivity(n);
                //showData();
                MainActivity.this.finish();
            }

        });
                //showData();

        mydb= new DatabaseHelper(this);
        Cursor cursor = mydb.getData();
        String[] fromFieldNames = new String[]{DatabaseHelper.KEY_TASK,DatabaseHelper.KEY_DATE};
        int[] toViewID =new int[] {R.id.itemTask,R.id.itemDate};
        SimpleCursorAdapter myCursor = new SimpleCursorAdapter(getBaseContext(),R.layout.item_list,cursor,fromFieldNames,toViewID,0);
        ListView myList = (ListView)findViewById(R.id.listViewTask);
        myList.setAdapter(myCursor);

        myList.setClickable(true);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                Intent nil = new Intent(MainActivity.this,Update.class);
                nil.putExtra("id", id);
                startActivity(nil);
                MainActivity.this.finish();

            }
        });

        listViewItemLongClick();

    }

    public void showData(){

        mydb= new DatabaseHelper(this);
        Cursor cursor = mydb.getData();
        String[] fromFieldNames = new String[]{DatabaseHelper.KEY_TASK,DatabaseHelper.KEY_DATE};
        int[] toViewID =new int[] {R.id.itemTask,R.id.itemDate};
        SimpleCursorAdapter myCursor = new SimpleCursorAdapter(getBaseContext(),R.layout.item_list,cursor,fromFieldNames,toViewID,0);
        ListView myLis = (ListView)findViewById(R.id.listViewTask);
        myLis.setAdapter(myCursor);

    }

    public void listViewClick(){

        ListView singleClick = (ListView)findViewById(R.id.listViewTask);
        singleClick.setClickable(true);
        singleClick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                Intent nil = new Intent(MainActivity.this,Update.class);
                //nil.putExtra("id", id);
                startActivity(nil);

            }
        });
    }

    public void listViewItemLongClick(){
        ListView myList = (ListView)findViewById(R.id.listViewTask);
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id){

                mydb.delete(id);
                showData();
                return false;
            }
        });
    }
}

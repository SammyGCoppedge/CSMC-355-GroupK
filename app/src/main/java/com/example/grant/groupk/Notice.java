package com.example.grant.groupk;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by megry on 4/30/2018.
 */

public class Notice extends AppCompatActivity {

    NoticeHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_notices);

        dbHelper = new NoticeHelper(this);

        lstTask = (ListView)findViewById(R.id.lstTask);

        loadTaskList();
    }

    private void loadTaskList() {
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter==null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.notices, R.id.task_title,taskList);
            lstTask.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        //Change menu icon color
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Notice")
                        .setMessage("What do you want to notify the user?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.insertNewTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView)parent.findViewById(R.id.task_title);
        Log.e("String", (String) taskTextView.getText());
        String task = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }
}
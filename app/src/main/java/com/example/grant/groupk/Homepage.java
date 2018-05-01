package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Grant on 4/2/2018.
 */

public class Homepage extends AppCompatActivity{ //Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test2, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent (this, Homepage.class));
                return true;
            case R.id.action_catalog:
                startActivity(new Intent (this, CatalogMain.class));
                return true;
            case R.id.action_profile:
                startActivity(new Intent (this, ProfilePage.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent (this, Setting.class));
                return true;
            case R.id.action_chat:
                startActivity(new Intent (this, ChatRoom.class));
                return true;
            case R.id.action_calender:
                startActivity(new Intent (this, CalendarActivity.class));
                return true;
            case R.id.action_map:
                startActivity(new Intent (this, MapsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void onCatalogClick(View v) {
        if (v.getId() == R.id.CatalogButton)
        {
            Intent i = new Intent (Homepage.this, CatalogMain.class);
            startActivity(i);
        }
    }

//    public void onForumClick(View v) {
//        if (v.getId() == R.id.ForumButton)
//        {
//            Intent i = new Intent (Homepage.this, Forum.class);
//            startActivity(i);
//        }
//    }

    public void onChatClick(View v) {
        if (v.getId() == R.id.ChatButton)
        {
            Intent i = new Intent (Homepage.this, ChatRoom.class);
            startActivity(i);
        }
    }

    public void onCalendarClick(View v) {
        if (v.getId() == R.id.CalendarButton)
        {
            Intent i = new Intent (Homepage.this, CalendarActivity.class);
            startActivity(i);
        }
    }

    public void onMapClick(View v) {
        if (v.getId() == R.id.MapButton)
        {
            Intent i = new Intent (Homepage.this, MapsActivity.class);
            startActivity(i);
        }
    }
    
    public void onSettingClick(View v) {
        if (v.getId() == R.id.SettingButton) {
            Intent i = new Intent(Homepage.this, Setting.class);
            startActivity(i);
        }

    }
}

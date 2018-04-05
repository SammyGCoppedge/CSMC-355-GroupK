package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Grant on 4/2/2018.
 */

public class Homepage extends AppCompatActivity{ //Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.textUsername);
        tv.setText(username);
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
}

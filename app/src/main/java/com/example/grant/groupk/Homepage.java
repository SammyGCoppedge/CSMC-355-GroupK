package com.example.grant.groupk;

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
        TextView tv = (TextView)findViewById(R.id.text);
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
    
    public void onSettingClick(View v) {
        if (v.getId() == R.id.SettingButton) {
            Intent i = new Intent(Homepage.this, Setting.class);
            startActivity(i);
        }

    }

    public void onCatalogClick(View v)
    {
        if (v.getId() == R.id.CatalogButton)
        {
            Intent i = new Intent(Homepage.this, NewArtifact.class);
            String username = getIntent().getStringExtra("Username");
            i.putExtra("Username", username);
            startActivity(i);
        }
    }
}

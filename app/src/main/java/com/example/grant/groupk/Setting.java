package com.example.grant.groupk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by megry on 4/4/2018.
 */

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
    }

    public void onContactClick(View v) {
        if (v.getId() == R.id.ContactButton)
        {
            Intent i = new Intent (Setting.this, Contact.class);
            startActivity(i);
        }
    }

    public void onLogOutClick(View v) {
        if (v.getId() == R.id.LogOutButton)
        {
            Intent i = new Intent (Setting.this, Welcome.class);
            startActivity(i);
        }
    }

    public void onVersionClick(View v) {
        if (v.getId() == R.id.ContactButton)
        {
            Intent i = new Intent (Setting.this, Version.class);
            startActivity(i);
        }
    }
}

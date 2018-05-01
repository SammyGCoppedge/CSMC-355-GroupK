package com.example.grant.groupk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toolbar;

public class Welcome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void onClickContinue(View v) {
        if (v.getId() == R.id.WelcomeButton)
        {
            Intent i = new Intent (Welcome.this, Login.class);
            startActivity(i);
        }
    }





}

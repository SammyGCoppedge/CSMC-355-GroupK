package com.example.grant.groupk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Grant on 4/2/2018.
 */

public class Homepage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.textUsername);
        tv.setText(username);
    }
}

package com.example.grant.groupk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by megry on 4/4/2018.
 */

public class CalendarContent extends AppCompatActivity {

    private static final String TAG = "CalendarContent";

    private TextView theDate;
    private Button GoBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        theDate = (TextView) findViewById(R.id.date);
        GoBackButton = (Button) findViewById(R.id.GoBackButton);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View view){
                        Intent intent = new Intent(CalendarContent.this, CalendarActivity.class);
                        startActivity(intent);
            }
        });
    }
}

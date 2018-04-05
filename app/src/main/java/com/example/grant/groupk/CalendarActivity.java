package com.example.grant.groupk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

/**
 * Created by megry on 4/4/2018.
 */

public class CalendarActivity extends AppCompatActivity{

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 +1) + "/" + i2 + "/" + i;
                Log.d(TAG,"onSelectedDayChange: date: "+ date);

                Intent intent = new Intent(CalendarActivity.this, CalendarContent.class);
                getIntent().putExtra("date",date);
                startActivity(intent);
            }
        });
    }

    public void onAddNewEventButtonClick(View v)
    {
        Intent i = new Intent(CalendarActivity.this, AddEvent.class);
        startActivity(i);
    }
}

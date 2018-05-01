package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddEvent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
    }

    public void onSubmitButtonClick(View v)
    {
        // TODO: add event to calendar
        Intent i = new Intent(AddEvent.this, CalendarActivity.class);
        startActivity(i);
    }

}

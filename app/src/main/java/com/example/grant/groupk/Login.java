package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Grant on 4/2/2018.
 */

public class Login extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onClickLogin(View v)
    {
        EditText u = (EditText)findViewById(R.id.textLogin);
        EditText e = (EditText)findViewById(R.id.textPassword);
        String username = u.getText().toString();
        String password = e.getText().toString();

        String realPassword = helper.searchPass(username);

        if ((username.trim().equals("") || username == null) || (password.trim().equals("") || password == null))
        {
            //Popup for nulls

            Toast entryError = Toast.makeText(Login.this, "Remove blank inputs", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else if (password.equals(realPassword))
        {
            Intent i = new Intent(Login.this, Homepage.class);
            i.putExtra("Username", username);
            startActivity(i);
        }
        else
        {
            //Popup for incorrect values

            Toast entryError = Toast.makeText(Login.this, "Username and password do not match.", Toast.LENGTH_LONG);
            entryError.show();
        }
    }

    public void onClickSignUp(View v)
    {
        if (v.getId() == R.id.SignUpButton)
        {
            Intent i = new Intent(Login.this, SignUp.class);
            startActivity(i);
        }
    }
}

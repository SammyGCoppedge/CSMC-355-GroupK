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

public class SignUp extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onClickConfirm(View v)
    {
        EditText uname = (EditText)findViewById(R.id.textUsername);
        EditText e = (EditText)findViewById(R.id.textEmail);
        EditText pw = (EditText)findViewById(R.id.textPassword);
        EditText pwc = (EditText)findViewById(R.id.textConfirmPass);

        String username = uname.getText().toString();
        String email = e.getText().toString();
        String password = pw.getText().toString();
        String passwordConfirm = pwc.getText().toString();

        if (username.trim().equals("") || username == null || email.trim().equals("") || email == null || password.trim().equals("") ||
                password == null || passwordConfirm.trim().equals("") || passwordConfirm == null)
        {
            //Popup for nulls

            Toast entryError = Toast.makeText(SignUp.this, "Remove blank inputs", Toast.LENGTH_SHORT);
            entryError.show();
        }
        if (!password.equals(passwordConfirm))
        {
            //Popup for mismatching password strings

            Toast entryError = Toast.makeText(SignUp.this, "Passwords do not match.", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else
        {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);

            if (helper.contains(user.getUsername(), user.getEmail()))
            {
                //Popup for already existing username/email

                Toast entryError = Toast.makeText(SignUp.this, "Username or Email already in use.", Toast.LENGTH_SHORT);
                entryError.show();
            }
            else
            {
                helper.insertUser(user);


                Intent i = new Intent(SignUp.this, Login.class);
                i.putExtra("username", username);
                i.putExtra("email", email);
                i.putExtra("password", password);
                startActivity(i);
            }
        }
    }
}

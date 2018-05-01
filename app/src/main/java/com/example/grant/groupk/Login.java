package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
/**
 * Created by Grant on 4/2/2018.
 */

public class Login extends AppCompatActivity {
    private EditText e;
    private EditText p;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        e = (EditText) findViewById(R.id.textEmail);
        p = (EditText) findViewById(R.id.textPassword);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    public void onClickLogin(View v)
    {

        String email = e.getText().toString().trim();
        String password = p.getText().toString().trim();



        if ((email.equals("") || email == null) || (password.equals("") || password == null))
        {
            //Popup for nulls

            Toast entryError = Toast.makeText(Login.this, "Remove blank inputs", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else
        {
           mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
               public void onComplete(Task<AuthResult> task){
                    if(task.isSuccessful()){
                        checkUser();
                    }
                    else{
                         Toast entryError = Toast.makeText(Login.this, "Username and password do not match.", Toast.LENGTH_LONG);
                         entryError.show();
                    }
                }

           });


        }

    }

    public void checkUser(){
        final String uid = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener(){
                public void onDataChange(DataSnapshot dataSnapshot){
                    if(dataSnapshot.hasChild(uid)){
                        startActivity(new Intent(Login.this, Homepage.class));
                    }
//                    else{
//                         Toast entryError = Toast.makeText(Login.this, "User does not exist", Toast.LENGTH_LONG);
//                         entryError.show();
//                    }
                }

                public void onCancelled(DatabaseError databaseError){
                }

        });
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

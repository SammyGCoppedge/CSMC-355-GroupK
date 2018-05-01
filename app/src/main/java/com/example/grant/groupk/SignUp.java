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
/**
 * Created by Grant on 4/2/2018.
 */

public class SignUp extends AppCompatActivity {

    private EditText uname, email, password;
    EditText pwc;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        uname = (EditText)findViewById(R.id.textUsername);
        email = (EditText)findViewById(R.id.textEmail);
        password = (EditText)findViewById(R.id.textPassword);
        pwc = (EditText)findViewById(R.id.textConfirmPass);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void onClickConfirm(View v)
    {
        final String usernameU, emailE, passwordP;

        usernameU = uname.getText().toString().trim();
        emailE = email.getText().toString().trim();
        passwordP = password.getText().toString().trim();
        String passwordConfirm = pwc.getText().toString().trim();

        if (usernameU.equals("") || usernameU == null || emailE.equals("") || emailE == null || passwordP.equals("") ||
                passwordP == null || passwordConfirm.equals("") || passwordConfirm == null)
        {
            //Popup for nulls

            Toast entryError = Toast.makeText(SignUp.this, "Remove blank inputs", Toast.LENGTH_SHORT);
            entryError.show();
        }
        if (!passwordP.equals(passwordConfirm))
        {
            //Popup for mismatching password strings

            Toast entryError = Toast.makeText(SignUp.this, "Passwords do not match.", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else
        {
           mAuth.createUserWithEmailAndPassword(emailE, passwordP).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                public void onComplete(Task<AuthResult> task){
                    if(task.isSuccessful()){
                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = mDatabase.child(user_id);
                        current_user_db.child("Name").setValue(usernameU);
                        startActivity(new Intent(SignUp.this, Login.class));
                    }
//                    else
//                        {
//                            Toast entryError = Toast.makeText(SignUp.this, "Email not exist", Toast.LENGTH_SHORT);
//                            entryError.show();
//                        }
                }
            });

        }
    }
}

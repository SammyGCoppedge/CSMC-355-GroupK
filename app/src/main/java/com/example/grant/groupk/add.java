package com.example.grant.groupk;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.List;

public class add extends Activity {
    private EditText title, description;
    Uri imageUrl;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private static final int PICK_IMAGE = 100;
    private Spinner spType;
    private Button button;
    private String tp;
    private ImageView imView;
    private StorageTask mUploadTask;
    private FirebaseUser mAuth;
    private DatabaseReference aDatabase;
    private String uid;
    private String authN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        button = (Button)findViewById(R.id.addImage_Button);
        title = (EditText)findViewById(R.id.editTitle);
        description = (EditText)findViewById(R.id.editDescription);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Artifacts");
        mStorageRef = FirebaseStorage.getInstance().getReference("Artifact_images");
        spType = (Spinner)findViewById(R.id.spinnerType);
        tp = "";

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        uid = mAuth.getUid();
        aDatabase = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Name");
        aDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                authN = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        imView = (ImageView) findViewById(R.id.imageAdd);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openPhoto();
            }
        });


        setSp();
    }

    public void setSp(){
        spType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tp = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
    }


    private void openPhoto(){

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE);

    }

    public void onClickSubmit(View v){
        final String titleT, descriptionD, imU, authU, typeP;

        titleT = title.getText().toString().trim();
        descriptionD = description.getText().toString().trim();
        imU = imageUrl.toString().trim();
        typeP = tp;
        authU = authN;

        if (titleT.equals("") || titleT == null)
        {
            Toast entryError = Toast.makeText(add.this, "title cannot be empty", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else if (imU.isEmpty() || imU == null)
        {
            Toast entryError = Toast.makeText(add.this, "add an image", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else if (titleT.length()>14)
        {
            Toast entryError = Toast.makeText(add.this, "cannot be more than 14 characters", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else{


            uploadFile(titleT, descriptionD, imU,typeP, authU );

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUrl = data.getData();
            imView.setImageURI(imageUrl);

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(final String titleT, final String descriptionD, final String imU, final String typeP, final String authU) {

        StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(imageUrl));

        mUploadTask = fileReference.putFile(imageUrl)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Post p = new Post(titleT, descriptionD, imU, typeP, authU);
                        String uploadId = mDatabase.push().getKey();

                        mDatabase.child(uploadId).setValue(p);
                        Intent i = new Intent(add.this, CatalogMain.class);
                        startActivity(i);
                    }
                }). addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(add.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

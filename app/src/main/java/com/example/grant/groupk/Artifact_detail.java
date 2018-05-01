package com.example.grant.groupk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class Artifact_detail extends AppCompatActivity {
    private ImageView artifactImg;
    private TextView artifactTitle;
    private TextView artifactDes;
    private TextView artifactAuth;
    private TextView artifactType;
    private String key;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artifact_detail);

        key = getIntent().getStringExtra("Key");

        artifactImg = findViewById(R.id.detail_image);
        artifactTitle = findViewById(R.id.detail_title);
        artifactDes = findViewById(R.id.detail_description);
        artifactAuth = findViewById(R.id.detail_author);
        artifactType = findViewById(R.id.detail_type);

        mDatabase = FirebaseDatabase.getInstance().getReference("Artifacts").child(key);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Post p = dataSnapshot.getValue(Post.class);

                artifactImg.setImageURI(Uri.parse(p.getImageU()));
                artifactTitle.setText(p.getTitle());
                artifactDes.setText(p.getDescription());
                artifactAuth.setText(p.getAuthor());
                artifactType.setText(p.getAuthor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

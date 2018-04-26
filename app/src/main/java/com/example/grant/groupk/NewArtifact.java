package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Grant on 4/25/2018.
 */

public class NewArtifact extends Activity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE = 1;
    Uri selectedImage;
    ArtifactDatabase db = new ArtifactDatabase(this);
    String today = "";
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newartifact);

        today = new SimpleDateFormat("yyyy/MM/dd HH:dd").format(new Date());

        username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.textUsername);
        tv.setText(username);

        TextView tv1 = (TextView)findViewById(R.id.postAuthor);
        tv1.setText(today);

        findViewById(R.id.artifactImage).setOnClickListener(this);
    }

    public void openImageChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }

    public void onClickAccept(View v)
    {
        if (v.getId() == R.id.acceptButton)
        {
            EditText artifactName = (EditText)findViewById(R.id.artifactName);
            EditText artifactLoaction = (EditText)findViewById(R.id.artifactLocation);
            EditText artifactBio = (EditText)findViewById(R.id.artifactBio);
            EditText artifactDesc = (EditText)findViewById(R.id.artifactDescription);

            String name = artifactName.getText().toString();
            String location = artifactLoaction.getText().toString();
            String bio = artifactBio.getText().toString();
            String desc = artifactDesc.getText().toString();

            if (name.trim().equals("") || name == null || location.trim().equals("") || location == null || bio.trim().equals("") || bio == null || desc.trim().equals("") || desc == null || selectedImage == null)
            {
                Toast entryError = Toast.makeText(NewArtifact.this, "Remove blank inputs", Toast.LENGTH_SHORT);
                entryError.show();
            }
            else
            {
                Artifact artifact = new Artifact(name, username, today, location, bio, desc, selectedImage.toString());

                if (db.contains(artifact.getName()))
                {
                    //Popup for already existing username/email

                    Toast entryError = Toast.makeText(NewArtifact.this, "Artifact with name already exists.", Toast.LENGTH_SHORT);
                    entryError.show();
                }
                else
                {
                    db.insertArtifact(artifact);
                    Intent i = new Intent(NewArtifact.this, ArtifactPage.class);

                        i.putExtra("Name", name);
                        i.putExtra("Username", username);
                        i.putExtra("Date", today);
                        i.putExtra("Bio", bio);
                        i.putExtra("Description", desc);
                        i.putExtra("Location", location);
                        i.putExtra("Image", selectedImage.toString());
                        startActivity(i);
                }
            }
        }
    }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data)
   {
       super.onActivityResult(requestCode, resultCode, data);
       if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE)
       {
           selectedImage = data.getData();
           if (selectedImage != null)
           {
               String path = getPathFromURI(selectedImage);
               Log.i("NewArtifact", "Image Path : " + path);
               ((ImageView) findViewById(R.id.artifactImage)).setImageURI(selectedImage);
           }
       }
   }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onClick(View v) {
        openImageChooser();
    }
}

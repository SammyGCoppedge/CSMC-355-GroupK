package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Grant on 4/30/2018.
 */

public class EditArtifact extends Activity implements View.OnClickListener {

    Uri selectedImage;
    private static final int RESULT_LOAD_IMAGE = 1;
    ArtifactDatabase db = new ArtifactDatabase(this);
    String today = "";
    String artifactName = "";
    static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editartifact);

        artifactName = getIntent().getStringExtra("Name");
        EditText name = (EditText) findViewById(R.id.editName);
        name.setText(artifactName);

        String imageString = getIntent().getStringExtra("Image");
        Uri artifactImage = Uri.parse(imageString);
        ImageView image = (ImageView) findViewById(R.id.editImage);
        image.setImageURI(artifactImage);

        username = getIntent().getStringExtra("Username");
        TextView poster = (TextView) findViewById(R.id.editUser);
        poster.setText(username);

        today = getIntent().getStringExtra("Date");
        TextView postDate = (TextView) findViewById(R.id.editDate);
        postDate.setText(today);

        String artifactBio = getIntent().getStringExtra("Bio");
        EditText bio = (EditText) findViewById(R.id.editBio);
        bio.setText(artifactBio);

        String artifactDetails = getIntent().getStringExtra("Description");
        EditText details = (EditText) findViewById(R.id.editDescription);
        details.setText(artifactDetails);

        String artifactLocation = getIntent().getStringExtra("Location");
        EditText location = (EditText) findViewById(R.id.editLocation);
        location.setText(artifactLocation);

        findViewById(R.id.editImage).setOnClickListener(this);
    }

    public void onClickDelete(View v)
    {
        Intent i = new Intent(EditArtifact.this, Homepage.class);
        Toast deletionMessage = Toast.makeText(EditArtifact.this, "Post deleted.", Toast.LENGTH_SHORT);
        deletionMessage.show();
        db.deleteRow(artifactName);
        i.putExtra("User", username);
        startActivity(i);
    }

    public void onClickEditAccept(View v)
    {
        if (v.getId() == R.id.editAcceptButton)
        {
            EditText artifactName = (EditText)findViewById(R.id.editName);
            EditText artifactLoaction = (EditText)findViewById(R.id.editLocation);
            EditText artifactBio = (EditText)findViewById(R.id.editBio);
            EditText artifactDesc = (EditText)findViewById(R.id.editDescription);

            String name = artifactName.getText().toString();
            String location = artifactLoaction.getText().toString();
            String bio = artifactBio.getText().toString();
            String desc = artifactDesc.getText().toString();

            if (name.trim().equals("") || name == null || location.trim().equals("") || location == null || bio.trim().equals("") || bio == null || desc.trim().equals("") || desc == null || selectedImage == null)
            {
                Toast entryError = Toast.makeText(EditArtifact.this, "Remove blank inputs", Toast.LENGTH_SHORT);
                entryError.show();
            }
            else
            {
                username = getIntent().getStringExtra("Username");

                Artifact artifact = new Artifact(name, username, today, location, bio, desc, selectedImage.toString());
                db.deleteRow(name);

                if (db.contains(artifact.getName()))
                {
                    //Popup for already existing username/email

                    Toast entryError = Toast.makeText(EditArtifact.this, "Artifact with name already exists.", Toast.LENGTH_SHORT);
                    entryError.show();
                }

                else
                {
                    db.insertArtifact(artifact);
                    Intent i = new Intent(EditArtifact.this, ArtifactPage.class);

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
                ((ImageView) findViewById(R.id.editImage)).setImageURI(selectedImage);
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

    public void openImageChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }
}


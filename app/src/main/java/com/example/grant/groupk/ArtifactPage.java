package com.example.grant.groupk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Grant on 4/25/2018.
 */

public class ArtifactPage extends Activity {

    static String artifactName = "";
    static String imageString = "";
    static String artifactPoster = "";
    static String artifactDate = "";
    static String artifactBio = "";
    static String artifactDetails = "";
    static String artifactLocation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artifactpage);

        try {
            artifactName = getIntent().getStringExtra("Name");
            TextView name = (TextView) findViewById(R.id.artifactName);
            name.setText(artifactName);

            imageString = getIntent().getStringExtra("Image");
            Uri artifactImage = Uri.parse(imageString);
            ImageView image = (ImageView) findViewById(R.id.image);
            image.setImageURI(artifactImage);

            artifactPoster = getIntent().getStringExtra("Username");
            TextView poster = (TextView) findViewById(R.id.postAuthor);
            poster.setText(artifactPoster);

            artifactDate = getIntent().getStringExtra("Date");
            TextView postDate = (TextView) findViewById(R.id.postDate);
            postDate.setText(artifactDate);

            artifactBio = getIntent().getStringExtra("Bio");
            TextView bio = (TextView) findViewById(R.id.bio);
            bio.setText(artifactBio);

            artifactDetails = getIntent().getStringExtra("Description");
            TextView details = (TextView) findViewById(R.id.details);
            details.setText(artifactDetails);

            artifactLocation = getIntent().getStringExtra("Location");
            TextView location = (TextView) findViewById(R.id.location);
            location.setText(artifactLocation);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void onClickEdit(View v)
    {
        if (v.getId() == R.id.editButton)
        {
            String currentUser = getIntent().getStringExtra("User");
            if (!artifactPoster.equals(currentUser))
            {
                Toast userError = Toast.makeText(ArtifactPage.this, "Not logged in as post author.", Toast.LENGTH_SHORT);
                userError.show();
            }
            Intent i = new Intent(ArtifactPage.this, EditArtifact.class);

            i.putExtra("Name", artifactName);
            i.putExtra("Image", imageString);
            i.putExtra("Username", artifactPoster);
            i.putExtra("Date", artifactDate);
            i.putExtra("Bio", artifactBio);
            i.putExtra("Description", artifactDetails);
            i.putExtra("Location", artifactLocation);
            startActivity(i);
        }
    }

    public void onClickGoHome(View v)
    {
        if (v.getId() == R.id.homeButton)
        {
            Intent i = new Intent(ArtifactPage.this, EditArtifact.class);
            i.putExtra("Username", getIntent().getStringExtra("User"));
            startActivity(i);
        }
    }
}
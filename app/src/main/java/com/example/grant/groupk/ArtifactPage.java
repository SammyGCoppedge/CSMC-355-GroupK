package com.example.grant.groupk;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Grant on 4/25/2018.
 */

public class ArtifactPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artifactpage);

        try {
            Bundle bundle = getIntent().getExtras();

            String artifactName = getIntent().getStringExtra("Name");
            TextView name = (TextView) findViewById(R.id.artifactName);
            name.setText(artifactName);

            String imageString = getIntent().getStringExtra("Image");
            Uri artifactImage = Uri.parse(imageString);
            ImageView image = (ImageView) findViewById(R.id.image);
            image.setImageURI(artifactImage);

            String artifactPoster = getIntent().getStringExtra("Username");
            TextView poster = (TextView) findViewById(R.id.postAuthor);
            poster.setText(artifactPoster);



            String artifactDate = getIntent().getStringExtra("Date");
            TextView postDate = (TextView) findViewById(R.id.postDate);
            postDate.setText(artifactDate);

            String artifactBio = getIntent().getStringExtra("Bio");
            TextView bio = (TextView) findViewById(R.id.bio);
            bio.setText(artifactBio);

            String artifactDetails = getIntent().getStringExtra("Description");
            TextView details = (TextView) findViewById(R.id.details);
            details.setText(artifactDetails);

            String artifactLocation = getIntent().getStringExtra("Location");
            TextView location = (TextView) findViewById(R.id.location);
            location.setText(artifactLocation);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}

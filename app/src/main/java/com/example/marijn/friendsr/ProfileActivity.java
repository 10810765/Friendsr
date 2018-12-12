package com.example.marijn.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class ProfileActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Retrieve the Friend object from the previous activity
        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // Get various id's
        ImageView pictureView = findViewById(R.id.pictureView);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView nameView = findViewById(R.id.nameView);
        TextView bioView = findViewById(R.id.bioView);

        // Use getName() on the retrieved Friend object
        name = retrievedFriend.getName();

        // Set the name, bio and picture of the retrieved Friend
        pictureView.setImageDrawable(getResources().getDrawable(retrievedFriend.getDrawableId()));
        nameView.setText(retrievedFriend.getName());
        bioView.setText(retrievedFriend.getBio());

        // Set an on rating change listener
        ratingBar.setOnRatingBarChangeListener(new ProfileRating());

        // Get the previously stored rating
        SharedPreferences prefs = getSharedPreferences(name, MODE_PRIVATE);
        Float StoredRating = prefs.getFloat("rating", 0);

        // Set the stored rating
        ratingBar.setRating(StoredRating);
    }

    // Create an on rating bar change listener
    private class ProfileRating implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            // Edit the old rating and store the new one
            SharedPreferences.Editor editor = getSharedPreferences(name, MODE_PRIVATE).edit();
            editor.putFloat("rating", rating);
            editor.apply();
        }
    }
}

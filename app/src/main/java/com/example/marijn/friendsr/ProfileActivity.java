package com.example.marijn.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class ProfileActivity extends AppCompatActivity {

    private String name; // Store the name
    private TextView favouriteText;
    private ToggleButton favouriteButton; // Store the ToggleButton ID

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
        favouriteText = findViewById(R.id.favText);
        favouriteButton = findViewById(R.id.favouriteButton);

        // Use getName() on the retrieved Friend object to be used later
        name = retrievedFriend.getName();

        // Set the name, bio and picture of the retrieved Friend
        pictureView.setImageDrawable(getResources().getDrawable(retrievedFriend.getDrawableId()));
        nameView.setText(retrievedFriend.getName());
        bioView.setText(retrievedFriend.getBio());

        // Set an on rating and on favourite change listener
        ratingBar.setOnRatingBarChangeListener(new ProfileRating());
        favouriteButton.setOnCheckedChangeListener(new ProfileFavourite());

        // Get a previously stored rating
        SharedPreferences prefs = getSharedPreferences("ratings", MODE_PRIVATE);
        Float StoredRating = prefs.getFloat(name, 0);

        // Set the previously stored rating
        ratingBar.setRating(StoredRating);

        // Get a previously stored favourite boolean
        SharedPreferences prefsFav = getSharedPreferences("favourite", MODE_PRIVATE);
        Boolean isFav = prefsFav.getBoolean(name.toLowerCase(), false);

        // Set the previously stored favourite state
        if (isFav) { // If this person was a favourite
            favouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellow_star));
            favouriteText.setText("This person is a favourite!");

        } else { // If this person was NOT a favourite
            favouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.grey_star));
            favouriteText.setText("Favourite this person:");
        }


    }

    // Create an on favourite click listener
    // With help from: https://stackoverflow.com/questions/34980309/
    private class ProfileFavourite implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isFavourite) {
            if (isFavourite) { // If favourited
                favouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellow_star));
                favouriteText.setText("This person is a favourite!");
            } else { // If unfavourited
                favouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.grey_star));
                favouriteText.setText("Favourite this person:");
            }

            // Edit the old favourite Boolean and store the new value
            SharedPreferences.Editor editor = getSharedPreferences("favourite", MODE_PRIVATE).edit();
            editor.putBoolean(name.toLowerCase(), isFavourite);
            editor.apply();
        }
    }

    // Create an on rating bar change listener
    private class ProfileRating implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            // Edit the old rating and store the new one
            SharedPreferences.Editor editor = getSharedPreferences("ratings", MODE_PRIVATE).edit();
            editor.putFloat(name, rating);
            editor.apply();
        }
    }
}

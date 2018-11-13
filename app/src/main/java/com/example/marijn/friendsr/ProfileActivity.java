package com.example.marijn.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        ImageView pictureView = findViewById(R.id.pictureView);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView nameView = findViewById(R.id.nameView);
        TextView bioView = findViewById(R.id.bioView);

        name = retrievedFriend.getName();

        //Set pictureView, nameView, bioView, ratingView
        pictureView.setImageDrawable(getResources().getDrawable(retrievedFriend.getDrawableId()));
        ratingBar.setOnRatingBarChangeListener(new ProfileRating());
        nameView.setText(retrievedFriend.getName());
        bioView.setText(retrievedFriend.getBio());

        SharedPreferences prefs = getSharedPreferences(name, MODE_PRIVATE);
        Float StoredRating = prefs.getFloat("rating", 0);

        if (StoredRating != 0) {
            ratingBar.setRating(StoredRating);
        }
        else {
            return;
        }
    }

    private class ProfileRating implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences.Editor editor = getSharedPreferences(name, MODE_PRIVATE).edit();
            editor.putFloat("rating", rating);
            editor.apply();
        }
    }
}

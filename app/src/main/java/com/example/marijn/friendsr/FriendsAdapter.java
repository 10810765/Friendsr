package com.example.marijn.friendsr;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class FriendsAdapter extends ArrayAdapter<Friend> {

    private ArrayList friends;
    private final Context context;

    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);
        this.context = context;
        friends = objects;
    }

    @NonNull
    @Override
    // Method that will be called every time a new grid item (friend) is to be displayed
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // If the convert view is null, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        // Get the profile name (TextView) and picture (ImageView) id
        TextView profileTitle = convertView.findViewById(R.id.profileName);
        ImageView profileImage = convertView.findViewById(R.id.profilePicture);
        ImageView favourite = convertView.findViewById(R.id.favouriteView);

        // Get the index of the Friend that we want to display
        Friend friendPosition = (Friend) friends.get(position);

        // Set the profile name and picture
        profileTitle.setText(friendPosition.getName());
        profileImage.setImageDrawable(getContext().getResources().getDrawable(friendPosition.getDrawableId()));

        // Get the name of the person
        String name = friendPosition.getName().toLowerCase();

        // Get the favourite status (boolean)
        SharedPreferences prefsFav = context.getSharedPreferences("favourite", 0);
        Boolean isFav = prefsFav.getBoolean(name, false);

        if (isFav) { // If it is a favourite person, make yellow star visible
            favourite.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}

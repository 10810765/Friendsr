package com.example.marijn.friendsr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class FriendsAdapter extends ArrayAdapter<Friend> {

    private ArrayList friends;

    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);
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
        TextView profileText = convertView.findViewById(R.id.profileName);
        ImageView profileImage = convertView.findViewById(R.id.profilePicture);

        // Get the index of the Friend that we want to display
        Friend friendPosition = (Friend) friends.get(position);

        // Set the profile name and picture
        profileText.setText(friendPosition.getName());
        profileImage.setImageDrawable(getContext().getResources().getDrawable(friendPosition.getDrawableId()));

        return convertView;
    }
}

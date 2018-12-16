package com.example.marijn.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class MainActivity extends AppCompatActivity {

    // Create an arrayList to store the sample friends
    ArrayList<Friend> friends = new ArrayList<>();

    private GridView grid; // Variable to hold the GridView ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the references to our images
        int arya = getResources().getIdentifier("arya", "drawable", getPackageName());
        int cersei = getResources().getIdentifier("cersei", "drawable", getPackageName());
        int daenerys = getResources().getIdentifier("daenerys", "drawable", getPackageName());
        int jaime = getResources().getIdentifier("jaime", "drawable", getPackageName());
        int jon = getResources().getIdentifier("jon", "drawable", getPackageName());
        int jorah = getResources().getIdentifier("jorah", "drawable", getPackageName());
        int margaery = getResources().getIdentifier("margaery", "drawable", getPackageName());
        int melisandre = getResources().getIdentifier("melisandre", "drawable", getPackageName());
        int sansa = getResources().getIdentifier("sansa", "drawable", getPackageName());
        int tyrion = getResources().getIdentifier("tyrion", "drawable", getPackageName());

        // Add sample friends to the arrayList
        friends.add(new Friend("Arya", "A girl needs a name", arya));
        friends.add(new Friend("Cersei", "Family is everything", cersei));
        friends.add(new Friend("Daenerys", "I love my dragons", daenerys));
        friends.add(new Friend("Jaime", "I miss my hand", jaime));
        friends.add(new Friend("Jon", "I know nothing", jon));
        friends.add(new Friend("Jorah", "At Daenerys' service", jorah));
        friends.add(new Friend("Margaery", "Power through relations!", margaery));
        friends.add(new Friend("Melisandre", "The lord of light will save us", melisandre));
        friends.add(new Friend("Sansa", "Winter is coming", sansa));
        friends.add(new Friend("Tyrion", "More wine!", tyrion));

        // Instantiate the adapter
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);

        // Get grid id
        grid = findViewById(R.id.gridDisplay);

        // Attach the adapter to the grid view
        grid.setAdapter(adapter);

        // Set on grid item click listener
        grid.setOnItemClickListener(new GridItemClickListener());
    }

    @Override // After a pause or restart
    public void onResume() {
        super.onResume();
        // Refresh the grid view
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        grid.setAdapter(adapter);
    }

    // Create an on Friend clicked listener
    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the Friend object of the clicked item in the grid view
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);

            // Pass the Friend object to the next activity
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}

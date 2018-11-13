package com.example.marijn.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        friends.add(new Friend("Arya", "A girl needs a name", arya));
        friends.add(new Friend("Cersei", "Family is everything", cersei));
        friends.add(new Friend("Daenerys", "I love my dragons", daenerys));
        friends.add(new Friend("Jaime", "I miss my hand", jaime));
        friends.add(new Friend("Jon", "I know nothing", jon));
        friends.add(new Friend("Jorah", "At Daenerys' service", jorah));
        friends.add(new Friend("Margaery", "Power by marriage", margaery));
        friends.add(new Friend("Melisandre", "The lord of light will save us", melisandre));
        friends.add(new Friend("Sansa", "Winter is coming", sansa));
        friends.add(new Friend("Tyrion", "More wine!", tyrion));

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);

        GridView grid = findViewById(R.id.gridDisplay);

        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new GridItemClickListener());
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}

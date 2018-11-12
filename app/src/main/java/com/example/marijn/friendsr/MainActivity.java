package com.example.marijn.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        friends.add(new Friend("arya", "-", arya));
        friends.add(new Friend("cersei", "-", cersei));
        friends.add(new Friend("daenerys", "-", daenerys));
        friends.add(new Friend("jaime", "-", jaime));
        friends.add(new Friend("jon", "-", jon));
        friends.add(new Friend("jorah", "-", jorah));
        friends.add(new Friend("margaery", "-", margaery));
        friends.add(new Friend("melisandre", "-", melisandre));
        friends.add(new Friend("sansa", "-", sansa));
        friends.add(new Friend("tyrion", "-", tyrion));

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);

        GridView grid = findViewById(R.id.gridDisplay);

        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new GridItemClickListener());
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("test", "Test message");
        }
    }
}

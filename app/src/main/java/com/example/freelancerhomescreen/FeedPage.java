package com.example.freelancerhomescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class FeedPage extends AppCompatActivity {
    private RecyclerView fRecyclerView;
    private ArrayList<Recycleritem> freelancers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.activity_feed_page);
        setUIRef();
        ArrayList<Freelancer> fl = db.getAllFreelancer();
        for(Freelancer f : fl){
            freelancers.add(new Recycleritem(f.getName(), f.getIndividualSkill(f.getSkills()), f.getDescription()));

        }
        BottomNavigationView bv = findViewById(R.id.bottomNavigationView);
        bv.setSelectedItemId(R.id.feedNav);
        bv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent i;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profileNav:
                        i = new Intent(FeedPage.this, ProfilePage.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.feedNav:
//                        i = new Intent(FeedPage.this, FeedPage.class);
//                        startActivity(i);
//                        finish();
                        fRecyclerView.getLayoutManager().scrollToPosition(0);
                        return true;
                    case R.id.settingsNav:
                        i = new Intent(FeedPage.this, SettingsPage.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }
    private void setUIRef()
    {
        //Reference of RecyclerView
        fRecyclerView = findViewById(R.id.freelancerList);
        //Linear Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FeedPage.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        fRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        RecyclerItemArrayAdapter myRecyclerViewAdapter = new RecyclerItemArrayAdapter(freelancers, new RecyclerItemArrayAdapter.MyRecyclerViewItemClickListener()
        {
            //Handling clicks
            @Override
            public void onItemClicked(Recycleritem country)
            {
                Toast.makeText(FeedPage.this, country.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    Log.d("test","oi" );
        //Set adapter to RecyclerView
        fRecyclerView.setAdapter(myRecyclerViewAdapter);
    }



}
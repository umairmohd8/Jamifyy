package com.example.jamifyy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamifyy.adapter.RecyclerViewAdapter;
import com.example.jamifyy.skeleton.SongInfo;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {
    private ArrayList<SongInfo> trackList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        recyclerView = findViewById(R.id.trackRecycler);
        trackList = new ArrayList<>();
        setSongInfo();
        setAdapter();

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void setAdapter(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(trackList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private void setSongInfo(){
        trackList.add(new SongInfo("hold on", "justin", 0,"1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0","https://i.scdn.co/image/ab67616d000048517359994525d219f64872d3b1" ));
    }
}

//https://open.spotify.com/track/1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0
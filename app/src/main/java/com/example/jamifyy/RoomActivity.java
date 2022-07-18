package com.example.jamifyy;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamifyy.adapter.RecyclerViewAdapter;
import com.example.jamifyy.backend.ParseSong;
import com.example.jamifyy.skeleton.SongInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RoomActivity extends AppCompatActivity {
    private ArrayList<SongInfo> trackList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    Timer timer = new Timer();
    private String aToken;
    private DatabaseReference databaseReference;
    String songLink;
    Button addButton;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        recyclerView = findViewById(R.id.trackRecycler);
        trackList = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(SongInfo.class.getSimpleName());
        //setSongInfo();
        setAdapter();


    }
    /*public RoomActivity(){
        trackList = new ArrayList<>();
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        retrieve();

    }

    private void freq(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                RoomActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        }, 0, 2000);
    }



    private void setAdapter(){
        this.adapter = new RecyclerViewAdapter(trackList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    SongInfo song1 = new SongInfo("Glimpse of You", "Joji", 0,"1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0","https://i.scdn.co/image/ab67616d000048517359994525d219f64872d3b1");
    private void setSongInfo(){
        trackList.add(song1);
        trackList.add(new SongInfo("Crash My Car", "COIN", 0,"1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0","https://i.scdn.co/image/ab67616d000048517359994525d219f64872d3b1" ));

    }
    public void setSongDB(View view){
        trackList.add(new SongInfo("Crash My Car", "COIN", 0,"1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0","https://i.scdn.co/image/ab67616d000048517359994525d219f64872d3b1" ));

        adapter.notifyDataSetChanged();
    }

    private void setSongDB(SongInfo song1){
        trackList.add(song1);
        adapter.notifyDataSetChanged();
    }


    public void popDialogue(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Add song");

        View v = getLayoutInflater().inflate(R.layout.songlink,null);
        EditText sLink = v.findViewById(R.id.songLinkText);
        Button addSong = v.findViewById(R.id.addSongButton);
        addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songLink = sLink.getText().toString();
                Log.d("RoomActivity", songLink);
                Log.i("test","clicked");
                dialog.dismiss();

                Bundle extras = getIntent().getExtras();
                if(extras != null){
                    aToken = extras.getString("aToken");
                }

                //calling the parse song class to parse the data of the song from the song link
                ParseSong par = new ParseSong();
                par.get_trackid(songLink,aToken);
            }
        });

        builder.setView(v);

        dialog = builder.create();
        dialog.show();

    }



    public void retrieve(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Log.i("parsesong", "datachanged");
                trackList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SongInfo songInfo = dataSnapshot.getValue(SongInfo.class);
                    assert songInfo != null;
                    Log.i("test", songInfo.getArtistName());
                    setSongDB(songInfo);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

//https://open.spotify.com/track/1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0
package com.example.jamifyy;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamifyy.adapter.RecyclerViewAdapter;
import com.example.jamifyy.backend.ParseSong;
import com.example.jamifyy.skeleton.SongInfo;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {
    private ArrayList<SongInfo> trackList;
    private RecyclerView recyclerView;
    private String aToken;
    String songLink;
    Button addButton;
    AlertDialog dialog;

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
        trackList.add(new SongInfo("Glimpse of Us", "Joji", 0,"1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0","https://i.scdn.co/image/ab67616d000048517359994525d219f64872d3b1" ));
        trackList.add(new SongInfo("Crash My Car", "COIN", 0,"1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0","https://i.scdn.co/image/ab67616d000048517359994525d219f64872d3b1" ));

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
                Log.d("test", songLink);
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
}

//https://open.spotify.com/track/1nahzW3kfMuwReTka28tH5?si=689b0a20ea1b44e0
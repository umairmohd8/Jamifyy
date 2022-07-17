package com.example.jamifyy.backend;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jamifyy.RoomActivity;
import com.example.jamifyy.skeleton.SongInfo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DAOSong {
    private DatabaseReference databaseReference;
    private ArrayList<SongInfo> newtrack;
    public DAOSong(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(SongInfo.class.getSimpleName());
    }

    public Task<Void> add(SongInfo song){
        return databaseReference.push().setValue(song);
    }

    public ValueEventListener retrieve(){
        return databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //newtrack = new ArrayList<SongInfo>();
                Log.i("parsesong", "datachanged");
                RoomActivity room1 = new RoomActivity();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SongInfo songInfo = dataSnapshot.getValue(SongInfo.class);
                    assert songInfo != null;
                    Log.i("parsesong-gotdb",songInfo.getArtistName());
                    //newtrack.add(songInfo);
                    room1.setNewSong(songInfo.getTrackName(),songInfo.getArtistName(),0,songInfo.getTrackID(),songInfo.getImageURL());
                    assert songInfo != null;
                    Log.i("parsesong-gotdb",songInfo.getArtistName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}

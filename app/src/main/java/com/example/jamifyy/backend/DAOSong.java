package com.example.jamifyy.backend;

import com.example.jamifyy.skeleton.SongInfo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOSong {
    private DatabaseReference databaseReference;
    public DAOSong(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(SongInfo.class.getSimpleName());
    }

    public Task<Void> add(SongInfo song){
        return databaseReference.push().setValue(song);
    }
}

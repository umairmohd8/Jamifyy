package com.example.jamifyy.backend;

import com.example.jamifyy.skeleton.SongInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOSong {
    private DatabaseReference databaseReference;
    public DAOSong(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(SongInfo.class.getSimpleName());
    }
}

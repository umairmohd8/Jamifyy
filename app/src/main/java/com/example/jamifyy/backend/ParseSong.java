package com.example.jamifyy.backend;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.jamifyy.connectors.SongService;

public class ParseSong {
    SongService songService = new SongService();
    private SharedPreferences sharedPreferences;
    private String accesstok;
    public void get_trackid(String url,String aToken){

        //selecting track id substring from the url of the track
        int pFrom = url.indexOf("track/") + "track/".length();
        int pTo = url.lastIndexOf("?");

        this.accesstok = aToken;

        String track_id = url.substring(pFrom, pTo);
        Log.i("track id is : ",track_id);
        songService.get_track_info(track_id,accesstok);
    }
}

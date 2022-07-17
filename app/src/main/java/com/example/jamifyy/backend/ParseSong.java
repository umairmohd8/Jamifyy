package com.example.jamifyy.backend;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jamifyy.RoomActivity;
import com.example.jamifyy.connectors.SongService;
import com.example.jamifyy.skeleton.SongInfo;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public void parse_json(JSONObject trackJson, String track_id) throws JSONException {
        Log.i("ParseSong", "Started parsing ");
        JSONObject album = trackJson.getJSONObject("album");
        JSONArray artists = album.getJSONArray("artists");
        JSONObject artists1 = artists.getJSONObject(0);
        Log.i("ParseSong ", artists1.toString());

        String aName = artists1.getString("name");
        Log.i("ParseSong ", "Artist name : "+aName);

        //JSONObject track = album.getJSONObject("name");
        String tName = trackJson.getString("name");
        Log.i("ParseSong ", "Song Name : "+tName);

        JSONArray images = album.getJSONArray("images");
        JSONObject image = images.getJSONObject(2);
        String track_url = image.getString("url");
        Log.i("ParseSong ", "Image url : "+track_url);
        Log.i("ParseSong ", "track id : "+track_id);



        //String trackName, String artistName, int upvotes, String trackID, String imageURL

        //creating a new song object
        SongInfo newSong = new SongInfo(tName,aName,0,track_id,track_url);

        //adding data to firebase DAO
        DAOSong dao = new DAOSong();
        dao.add(newSong).addOnSuccessListener(suc ->
        {
            //Toast.makeText(,"Song added",Toast.LENGTH_SHORT).show();
            Log.i("ParseSong", "song added");
        }).addOnFailureListener(er->
        {
            //Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            Log.i("ParseSong", "error adding song"+er.getMessage());

        });







    }

}

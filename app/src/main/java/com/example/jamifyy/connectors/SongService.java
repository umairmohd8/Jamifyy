package com.example.jamifyy.connectors;

import android.app.DownloadManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jamifyy.SplashActivity;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SongService {

    Call mCall;
    private final OkHttpClient client = new OkHttpClient();
    public void get_track_info(String track_id, String access_token){
        String url_str = "https://api.spotify.com/v1/tracks/" +track_id;
        Log.i("url",track_id);
        String bear = "Bearer " + access_token;
        final Request request = new Request.Builder()
                .url(url_str)
                .addHeader("Authorization",bear)
                .build();

        cancelCall();
        mCall = client.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("Request error",e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    final JSONObject jsonObject = new JSONObject(response.body().string());
                    Log.i("Response String",jsonObject.toString(3));

                } catch (Exception e){
                    Log.i("Json parsing","failed to parse json data " + e.getMessage());
                }
            }
        });
    }

    private void cancelCall() {
        if (mCall != null) {
            mCall.cancel();
        }
    }
}

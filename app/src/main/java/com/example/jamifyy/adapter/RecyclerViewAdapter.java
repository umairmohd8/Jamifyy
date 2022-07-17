package com.example.jamifyy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamifyy.R;
import com.example.jamifyy.connectors.SongService;
import com.example.jamifyy.skeleton.SongInfo;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<SongInfo>  trackList;
    private SongService songService;

    public  RecyclerViewAdapter(ArrayList<SongInfo> trackList){
        this.trackList = trackList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView trackName;
        private TextView artistName;
        private Button upvote;
        private ImageView trackImage;

        public MyViewHolder(final View view){
            super(view);
            trackName = view.findViewById(R.id.songName);
            artistName = view.findViewById(R.id.artist);
            trackImage = view.findViewById(R.id.songImage);

        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        String tName = trackList.get(position).getTrackName();
        String aName = trackList.get(position).getArtistName();
        //String tName = trackList.get(position).getTrackName();
        holder.trackName.setText(tName);
        holder.artistName.setText(aName);

    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }
}

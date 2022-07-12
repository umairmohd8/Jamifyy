package com.example.jamifyy.skeleton;

public class SongInfo {
    private String trackName;
    private String artistName;
    private String trackID;
    private String imageURL;

    private int upvotes;

    public SongInfo(String trackName, String artistName, int upvotes, String trackID, String imageURL) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.upvotes = upvotes;
        this.trackID = trackID;
        this.imageURL = imageURL;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}

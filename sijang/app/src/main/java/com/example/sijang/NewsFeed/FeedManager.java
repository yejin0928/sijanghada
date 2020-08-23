package com.example.sijang.NewsFeed;

public class FeedManager {

    private int _id;
    private String hash;
    private byte[] image;

    public FeedManager(int _id, String hash, byte[] image) {
        this._id = _id;
        this.hash = hash;
        this.image = image;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

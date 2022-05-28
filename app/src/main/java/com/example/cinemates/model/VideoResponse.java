package com.example.cinemates.model;

import java.util.ArrayList;

/**
 * @author Antonio Di Nuzzo
 * Created 28/05/2022 at 11:12
 */
public class VideoResponse {
    private int id;
    private ArrayList<Video> results;

    public VideoResponse(int id, ArrayList<Video> results) {
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Video> getResults() {
        return results;
    }

    public void setResults(ArrayList<Video> results) {
        this.results = results;
    }
}

package com.example.cinemates.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 20:27
 */
public class Collection implements Serializable {
    private int id;
    private String name, poster_path, backdrop_path, overview;
    private ArrayList<Movie> parts;

    public Collection(int id, String name, String poster_path, String backdrop_path, String overview, ArrayList<Movie> parts) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.parts = parts;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<Movie> getParts() {
        return parts;
    }

    private void setParts(ArrayList<Movie> parts) {
        this.parts = parts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                '}';
    }
}

package com.example.cinemates.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
public class Actor extends Person implements Serializable {
    private String birthday, biography, place_of_birth, deathDay;
    private JsonObject movie_credits;
    private String[] also_known_as;

    public Actor(String birthday, String name, String biography, String place_of_birth,
                 String profile_path,  Integer id,
                 Number popularity, String deathday, JsonObject movie_credits, String[] also_known_as) {
        super(name, profile_path, id, popularity);
        this.birthday = birthday;
        this.biography = biography;
        this.place_of_birth = place_of_birth;
        this.deathDay = deathday;
        this.movie_credits = movie_credits;

        this.also_known_as = also_known_as;
    }

    public JsonObject getMovie_credits() {
        return movie_credits;
    }

    public void setMovie_credits(JsonObject movie_credits) {
        this.movie_credits = movie_credits;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathDay() {
        return deathDay;
    }

    public String[] getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(String[] also_known_as) {
        this.also_known_as = also_known_as;
    }

    public void setDeathDay(String deathDay) {
        this.deathDay = deathDay;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "birthday='" + birthday + '\'' +
                ", biography='" + biography + '\'' +
                ", place_of_birth='" + place_of_birth + '\'' +
                ", deathday='" + deathDay + '\'' +
                ", movie_credits=" + movie_credits +
                ", also_known_as=" + Arrays.toString(also_known_as) +
                '}';
    }
}

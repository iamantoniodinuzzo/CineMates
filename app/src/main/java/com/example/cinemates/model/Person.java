package com.example.cinemates.model;

import java.io.Serializable;

/**
 * @author Antonio Di Nuzzo
 * Created 31/05/2022 at 11:32
 */
public class Person implements Serializable {
    private String name, profile_path;
    private Integer id;
    private Number popularity;



    public Person(String name, String profile_path, Integer id, Number popularity) {
        this.name = name;
        this.profile_path = profile_path;
        this.id = id;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", profile_path='" + profile_path + '\'' +
                ", id=" + id +
                ", popularity=" + popularity +
                '}';
    }
}

package com.example.cinemates.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cinemates.util.Converters;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Antonio Di Nuzzo
 * Created 31/05/2022 at 11:32
 */
@Entity
public class Person implements Serializable {
    private String name, profile_path;
    @PrimaryKey
    private Integer id;
    private boolean favorite;
    @TypeConverters(Converters.class)
    private Number popularity;

    @Ignore
    public Person(String name, String profile_path, Integer id, Number popularity) {
        this.name = name;
        this.profile_path = profile_path;
        this.id = id;
        this.popularity = popularity;
    }

    public Person(String name, String profile_path, Integer id, Number popularity, boolean favorite) {
        this.name = name;
        this.profile_path = profile_path;
        this.id = id;
        this.popularity = popularity;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setFavorite() {
        this.favorite = !this.favorite;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName()) && getId().equals(person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
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

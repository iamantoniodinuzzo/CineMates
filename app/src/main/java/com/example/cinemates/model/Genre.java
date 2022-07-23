package com.example.cinemates.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
public class Genre implements Serializable {
    private Integer id;
    private String name;

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return getId().equals(genre.getId()) && Objects.equals(getName(), genre.getName());
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}

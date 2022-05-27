package com.example.cinemates.model;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
public class Cast {
    private String character, name, profile_path, known_for_department, original_name;
    private Integer id;
    private Number popularity;

    public Cast(String character, String name, String profile_path, String known_for_department, String original_name, Integer id, Number popularity) {
        this.character = character;
        this.name = name;
        this.profile_path = profile_path;
        this.known_for_department = known_for_department;
        this.original_name = original_name;
        this.id = id;
        this.popularity = popularity;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
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

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public Number getPopularity() {
        return popularity;
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
}

package com.example.cinemates.model;

import java.io.Serializable;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
public class Cast extends Person implements Serializable {
    private String character, original_name;

    public Cast(String character, String name, String profile_path, String known_for_department, String original_name, Integer id, Number popularity) {
        super(name, profile_path, known_for_department, id, popularity);
        this.character = character;
        this.original_name = original_name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getOriginal_name() {
        return original_name;
    }

}

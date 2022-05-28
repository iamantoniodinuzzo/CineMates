package com.example.cinemates.util;

import androidx.annotation.NonNull;

/**
 * @author Antonio Di Nuzzo
 * Created 28/05/2022 at 10:30
 */
public enum MediaType {
    ALL("all"), MOVIE("movie"), TV("tv"), PERSON("person");
    private final String value;

    MediaType(@NonNull String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

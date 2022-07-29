package com.example.cinemates.util;

import androidx.room.TypeConverter;

import com.example.cinemates.model.Genre;
import com.example.cinemates.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:47
 */
public class Converters {

    @TypeConverter
    public static Movie.PersonalStatus toPersonalStatus(int status) {
        if (status == Movie.PersonalStatus
                .TO_SEE.getStatus()) {
            return Movie.PersonalStatus.TO_SEE;
        } else if (status == Movie.PersonalStatus
                .SEEN.getStatus()) {
            return Movie.PersonalStatus.SEEN;
        } else if (status == Movie.PersonalStatus.EMPTY.getStatus()) {
            return Movie.PersonalStatus.EMPTY;
        }
        throw new IllegalArgumentException("Could not recognize status");
    }

    @TypeConverter
    public static Integer toInteger(Movie.PersonalStatus status) {
        return status.getStatus();
    }

    @TypeConverter
    public static Integer toInteger(Number number) {
        return number.intValue();
    }

    @TypeConverter
    public static ArrayList<Genre> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Genre>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Genre> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}

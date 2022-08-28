package com.example.cinemates.util;

import androidx.room.TypeConverter;

import com.example.cinemates.model.data.Collection;
import com.example.cinemates.model.data.Genre;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.PersonalStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:47
 */
public class Converters {

    @TypeConverter
    public static PersonalStatus toPersonalStatus(int status) {
        if (status == PersonalStatus
                .TO_SEE.getStatus()) {
            return PersonalStatus.TO_SEE;
        } else if (status == PersonalStatus
                .SEEN.getStatus()) {
            return PersonalStatus.SEEN;
        } else if (status == PersonalStatus.EMPTY.getStatus()) {
            return PersonalStatus.EMPTY;
        }
        throw new IllegalArgumentException("Could not recognize status");
    }

    @TypeConverter
    public static Integer toInteger(PersonalStatus status) {
        return status.getStatus();
    }


    @TypeConverter
    public static List<Genre> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Genre>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<Genre> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static Collection fromValue(String value) {
        Type listType = new TypeToken<Collection>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromCollection(Collection list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}

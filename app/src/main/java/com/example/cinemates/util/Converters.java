package com.example.cinemates.util;

import androidx.room.TypeConverter;

import com.example.cinemates.model.Movie;

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
        }else if(status == Movie.PersonalStatus.EMPTY.getStatus()){
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
}

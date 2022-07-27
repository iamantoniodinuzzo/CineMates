package com.example.cinemates.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cinemates.local.dao.MovieDao;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.Converters;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:13
 */
@Database(entities = {Movie.class}, version = 4)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao mMovieDao();
}

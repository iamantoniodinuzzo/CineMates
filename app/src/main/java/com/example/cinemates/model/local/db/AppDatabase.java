package com.example.cinemates.model.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.local.dao.MovieDao;
import com.example.cinemates.model.local.dao.PersonDao;
import com.example.cinemates.model.data.Person;
import com.example.cinemates.util.Converters;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:13
 */
@Database(entities = {Movie.class}, version = 7)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao mMovieDao();
//    public abstract PersonDao mPersonDao();
}

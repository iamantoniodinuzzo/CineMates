package com.example.cinemates.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinemates.model.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie WHERE favorite = 1")
    Observable<List<Movie>> getAllFavorite();

    @Query("SELECT * FROM movie WHERE personalStatus = :status")
    Observable<List<Movie>> getAllWithStatus(Movie.PersonalStatus status);

    @Query("SELECT * FROM movie WHERE id = :id ")
    Movie retrieveMovie(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Movie... movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Movie movie);

    @Delete
    void delete(Movie movie);
}

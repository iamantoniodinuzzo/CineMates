package com.example.cinemates.model.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cinemates.model.data.Cast;
import com.example.cinemates.model.data.Person;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
public interface PersonDao {
    @Query("SELECT * FROM person WHERE isFavorite = 1")
    Observable<List<Person>> getAllFavorite();


    @Query("SELECT * FROM person WHERE id = :id ")
    Person retrievePerson(Integer id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Person person);


    @Delete
    void delete(Person person);
}

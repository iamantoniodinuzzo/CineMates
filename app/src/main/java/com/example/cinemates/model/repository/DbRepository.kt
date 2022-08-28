package com.example.cinemates.model.repository

import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import javax.inject.Inject
import com.example.cinemates.model.local.db.AppDatabase
import com.example.cinemates.model.local.dao.MovieDao
import com.example.cinemates.model.local.dao.PersonDao
import com.example.cinemates.model.data.PersonalStatus
import io.reactivex.rxjava3.core.Observable
import java.util.HashMap

private const val TAG = "DbRepository"

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:23
 */
class DbRepository
@Inject
constructor(appDatabase: AppDatabase) {
    companion object {
        private lateinit var movieDao: MovieDao
        private lateinit var personDao: PersonDao
    }

    init {
        movieDao = appDatabase.mMovieDao()
        personDao = appDatabase.mPersonDao()
    }

    fun getMovies() = movieDao.getMovies()
    fun isMovieFavorite(id: Int) = movieDao.isMovieFavorite(id)
    suspend fun insertMovie(movie: Movie) = movieDao.insert(movie)
    suspend fun updateMovie(movie: Movie) = movieDao.update(movie)
    suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)

    fun getPersons() = personDao.getPersons()
    fun isPersonFavorite(id: Int) = personDao.isPersonFavorite(id)
    suspend fun insertPerson(person: Person) = personDao.insert(person)
    suspend fun updatePerson(person: Person) = personDao.update(person)
    suspend fun deletePerson(person: Person) = personDao.delete(person)


}
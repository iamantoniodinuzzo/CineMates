package com.example.cinemates.model.repository

import com.example.cinemates.model.entities.Movie
import com.example.cinemates.model.local.dao.MovieDao
import com.example.cinemates.model.local.db.AppDatabase
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:23
 */
class DbMovieRepository
@Inject
constructor(appDatabase: AppDatabase) {
    companion object {
        private lateinit var movieDao: MovieDao
    }

    init {
        movieDao = appDatabase.movieDao()
    }

    fun getMovies() = movieDao.getAll()
    fun getMovie(id: Int) = movieDao.getById(id)
    fun getToSeeMovies() = movieDao.getToSeeMovies()
    fun getSeenMovies() = movieDao.getSeenMovies()
    fun getFavoriteMovies() = movieDao.getFavoriteMovies()
    fun isMovieFavorite(id: Int) = movieDao.isFavorite(id)
    fun isMovieToSee(id: Int) = movieDao.isToSee(id)
    fun isMovieSeen(id: Int) = movieDao.isSeen(id)
    fun insertMovie(movie: Movie) = movieDao.insert(movie)
    fun updateMovie(movie: Movie) = movieDao.update(movie)
    fun deleteMovie(movie: Movie) = movieDao.delete(movie)

}
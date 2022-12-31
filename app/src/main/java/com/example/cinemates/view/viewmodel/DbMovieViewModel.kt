package com.example.cinemates.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Movie
import com.example.cinemates.model.PersonalStatus
import com.example.cinemates.repository.DbMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DBViewModel"

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:33
 */
@HiltViewModel
class DbMovieViewModel
@Inject
constructor(
    private val dbMovieRepository: DbMovieRepository
) : ViewModel() {


    init {
        initAllMoviesLists()
    }


    private val _favorites = MutableLiveData<List<Movie>>()
    val favorites: LiveData<List<Movie>> get() = _favorites
    private val _toSee = MutableLiveData<List<Movie>>()
    val toSee: LiveData<List<Movie>> get() = _toSee
    private val _seen = MutableLiveData<List<Movie>>()
    val seen: LiveData<List<Movie>> get() = _seen
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies


    private fun insertMovie(movie: Movie) = viewModelScope.launch {
        dbMovieRepository.insertMovie(movie)
    }

    private fun deleteMovie(movie: Movie) = viewModelScope.launch {
        dbMovieRepository.deleteMovie(movie)
    }


    private fun updateMovie(movie: Movie) = viewModelScope.launch {
        dbMovieRepository.updateMovie(movie)
    }

    /**
     * Check if the movie is already on the list of favorites.
     * @return True if is my favorite movie, False instead
     */
    private fun isMyFavoriteMovie(movie: Movie): Boolean =
        dbMovieRepository.isMovieFavorite(movie.id)

    /**
     * Check if the [Movie] is already on the list of toSee.
     * @return True if is a toSee movie, False instead
     */
    private fun isMovieToSee(movie: Movie): Boolean = dbMovieRepository.isMovieToSee(movie.id)

    /**
     * Check if the [Movie] is already on the list of seen.
     * @return True if is seen movie, False instead
     */
    private fun isMovieSeen(movie: Movie): Boolean = dbMovieRepository.isMovieSeen(movie.id)


    /**
     * Check if the [Movie] to be included is already a favorite.
     * If true, the [Movie] is removed otherwise it is added to favorites.
     * @return True if is set as favorite, false if it was favorite and is removed
     */
    fun setAsFavorite(movie: Movie): Boolean {
        val isSetAsFav = !isMyFavoriteMovie(movie)
        movie.favorite = isSetAsFav
        insertMovie(movie)
        deleteIfNotSignificant(movie)
        return isSetAsFav
    }


    fun getMovie(id: Int): Movie? = dbMovieRepository.getMovie(id)

    /**
     * Set and update [PersonalStatus] of the movie.
     * @return True if movie is set into a specific list, false otherwise ([PersonalStatus.EMPTY]).
     */
    fun updatePersonalStatus(movie: Movie, status: PersonalStatus): Boolean {
        val result = when (status) {
            PersonalStatus.TO_SEE -> {
                if (isMovieToSee(movie)) {
                    movie.personalStatus = PersonalStatus.EMPTY
                    false
                } else {
                    movie.personalStatus = PersonalStatus.TO_SEE
                    true
                }
            }
            PersonalStatus.SEEN -> {
                if (isMovieSeen(movie)) {
                    movie.personalStatus = PersonalStatus.EMPTY
                    false
                } else {
                    movie.personalStatus = PersonalStatus.SEEN
                    true
                }
            }
            else -> false
        }
        insertMovie(movie)
        deleteIfNotSignificant(movie)

        return result
    }

    /**
     * An insignificant movie is not favorite and has [PersonalStatus.EMPTY].
     * If @param movie has both, there is no update/insert only a delete
     */
    private fun deleteIfNotSignificant(movie: Movie) {
        if (!movie.favorite && movie.personalStatus == PersonalStatus.EMPTY)
            deleteMovie(movie)

    }


    /**
     * Sums the duration of all movies whose state is the one given as parameter and returns a string.
     * If @param status is [PersonalStatus.EMPTY], sum all other movies' runtime that are in the db.
     * If @param status is [PersonalStatus.TO_SEE] or [PersonalStatus.SEEN], sum all corresponding category runtime
     * @return A string indicating the total number of hours.
     */
    fun getTotalHoursOf(status: PersonalStatus): Int {
        val totalMinutes = when (status) {
            PersonalStatus.TO_SEE -> calculateTotalMinutes(toSee.value)
            PersonalStatus.SEEN -> calculateTotalMinutes(seen.value)
            PersonalStatus.EMPTY -> calculateTotalMinutes(movies.value)
        }
        return (totalMinutes / 60)
    }

    fun getSizeOf(status: PersonalStatus): Int {
        return when (status) {
            PersonalStatus.TO_SEE -> toSee.value?.size ?: 0
            PersonalStatus.SEEN -> seen.value?.size ?: 0
            PersonalStatus.EMPTY -> movies.value?.size ?: 0
        }

    }

    private fun calculateTotalMinutes(movies: List<Movie>?): Int {
        var totalMinutes = 0

        movies?.forEach { movie ->
            if (movie.runtime != null)
                totalMinutes += movie.runtime
        }

        return totalMinutes
    }

    private fun initAllMoviesLists() {
        dbMovieRepository.apply {
            getFavoriteMovies()
                .mapLatest { list ->
                    _favorites.value = list
                }
                .launchIn(viewModelScope)

            getToSeeMovies()
                .mapLatest { list ->
                    _toSee.value = list
                }
                .launchIn(viewModelScope)

            getSeenMovies()
                .mapLatest { list ->
                    _seen.value = list
                }
                .launchIn(viewModelScope)
            getMovies()
                .mapLatest { list ->
                    _movies.value = list
                }
                .launchIn(viewModelScope)
        }

    }


}
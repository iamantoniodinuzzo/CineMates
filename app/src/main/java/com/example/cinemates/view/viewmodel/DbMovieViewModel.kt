package com.example.cinemates.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.PersonalStatus
import com.example.cinemates.model.repository.DbMovieRepository
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

    fun updateMovie(movie: Movie) = viewModelScope.launch {
        dbMovieRepository.updateMovie(movie)
    }

    /**
     * Check if the movie is already on the list of favorites.
     * @return True if is my favorite movie, False instead
     */
    fun isMyFavoriteMovie(movie: Movie): Boolean = dbMovieRepository.isMovieFavorite(movie.id)

    /**
     * Check if the [Movie] is already on the list of toSee.
     * @return True if is a toSee movie, False instead
     */
    fun isMovieToSee(movie: Movie): Boolean = dbMovieRepository.isMovieToSee(movie.id)

    /**
     * Check if the [Movie] is already on the list of seen.
     * @return True if is seen movie, False instead
     */
    fun isMovieSeen(movie: Movie): Boolean = dbMovieRepository.isMovieSeen(movie.id)


    /**
     * Check if the [Movie] to be included is already a favorite.
     * If true, the [Movie] is removed otherwise it is added to favorites.
     * @return True if is set as favorite, false if it was favorite and is removed
     */
    fun setAsFavorite(movie: Movie): Boolean {
        return if (isMyFavoriteMovie(movie)) {
            deleteMovie(movie)
            false
        } else {
            insertMovie(movie)
            true
        }
    }

    /**
     * Update the [PersonalStatus] of the [Movie]
     *
     * For example, you want to add a [Movie] to the '[PersonalStatus.TO_SEE]' list so the method is called by passing the appropriate parameters.
     * If the [Movie] was already in the '[PersonalStatus.TO_SEE]' list, the [Movie] is removed from that list, otherwise it is inserted
     *
     * @return True if the film was not already in that state and then updates it,
     * false if the film was already in that state and removes it from that state.
     */
    fun updatePersonalStatus(movie: Movie, status: PersonalStatus): Boolean {
        val result: Boolean = when (status) {
            PersonalStatus.SEEN -> isMovieSeen(movie)
            PersonalStatus.TO_SEE -> isMovieToSee(movie)
            PersonalStatus.EMPTY -> false
        }
            insertMovie(movie)
        return result
    }


    /**
     * Sums the duration of all movies whose state is the one given as parameter and returns a string.
     * If @param status is [PersonalStatus.EMPTY], sum all movies' runtime
     * @return A string indicating the total number of hours.
     */
    fun getTotalHoursOf(status: PersonalStatus): String {
        val totalMinutes = when (status) {
            PersonalStatus.TO_SEE -> calculateTotalHours(toSee.value)
            PersonalStatus.SEEN -> calculateTotalHours(seen.value)
            PersonalStatus.EMPTY -> calculateTotalHours(movies.value)
        }
        return (totalMinutes / 60).toString()
    }

    private fun calculateTotalHours(movies: List<Movie>?): Int {
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
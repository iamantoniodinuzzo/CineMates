package com.example.cinemates.view.dbviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Movie
import com.example.cinemates.model.PersonalStatus
import com.example.cinemates.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val movieRepository: MovieRepository
) : ViewModel() {


    init {
        initAllPersonalMovieLists()
    }


    val favorites: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val toSee: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val seen: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())


    private fun insertMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.insertMovie(movie)
    }

    private fun deleteMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.deleteMovie(movie)
    }

    /**
     * Check if the movie is already on the list of favorites.
     * @return True if is my favorite movie, False instead
     */
    private fun isMyFavoriteMovie(movie: Movie): Boolean =
        movieRepository.isMovieFavorite(movie.id)

    /**
     * Check if the [Movie] is already on the list of toSee.
     * @return True if is a toSee movie, False instead
     */
    private fun isMovieToSee(movie: Movie): Boolean = movieRepository.isMovieToSee(movie.id)

    /**
     * Check if the [Movie] is already on the list of seen.
     * @return True if is seen movie, False instead
     */
    private fun isMovieSeen(movie: Movie): Boolean = movieRepository.isMovieSeen(movie.id)


    /**
     * Check if the [Movie] to be included is already a favorite.
     * If true, the [Movie] is removed otherwise it is added to favorites.
     * @return True if is set as favorite, false if it was favorite and is removed
     */
    fun setAsFavorite(movie: Movie): Boolean {
        val isSetAsFav = !isMyFavoriteMovie(movie)
        movie.favorite = isSetAsFav
        if (!isSignificant(movie))
            deleteMovie(movie)
        else
            insertMovie(movie)
        return isSetAsFav
    }


    fun getMovie(id: Int): Movie? = movieRepository.getMovie(id)

    /**
     * Set and update [PersonalStatus] of the movie.
     * @return True if movie is set into a specific list, false otherwise ([PersonalStatus.EMPTY]).
     */
    fun updatePersonalStatus(movie: Movie?, status: PersonalStatus): Movie? {
        movie?.let {
            val result = when (status) {
                PersonalStatus.TO_SEE -> {
                    if (isMovieToSee(movie)) {
                        movie.personalStatus = PersonalStatus.EMPTY
                        movie
                    } else {
                        movie.personalStatus = PersonalStatus.TO_SEE
                        movie
                    }
                }
                PersonalStatus.SEEN -> {
                    if (isMovieSeen(movie)) {
                        movie.personalStatus = PersonalStatus.EMPTY
                        movie
                    } else {
                        movie.personalStatus = PersonalStatus.SEEN
                        movie
                    }
                }
                else -> movie
            }
            if (!isSignificant(movie))
                deleteMovie(movie)
            else
                insertMovie(movie)

            return result
        }
        return null

    }

    /**
     * An insignificant movie is not favorite and has [PersonalStatus.EMPTY].
     * if movie has both, there is no update/insert only a delete
     * @param movie
     */
    private fun isSignificant(movie: Movie): Boolean {
        return movie.favorite && movie.personalStatus != PersonalStatus.EMPTY
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
            PersonalStatus.TO_SEE -> toSee.value.size
            PersonalStatus.SEEN -> seen.value.size
            PersonalStatus.EMPTY -> movies.value.size
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

    private fun initAllPersonalMovieLists() = viewModelScope.launch {
        movieRepository.apply {
            getFavoriteMovies()
                .mapLatest { list ->
                    favorites.value = list
                }

            getToSeeMovies()
                .mapLatest { list ->
                    toSee.value = list
                }

            getSeenMovies()
                .mapLatest { list ->
                    seen.value = list
                }
            getMovies()
                .mapLatest { list ->
                    movies.value = list
                }
        }
    }


}
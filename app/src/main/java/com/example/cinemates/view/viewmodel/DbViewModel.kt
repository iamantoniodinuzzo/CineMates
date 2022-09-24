package com.example.cinemates.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.model.data.PersonalStatus
import com.example.cinemates.model.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DBViewModel"

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:33
 */
@HiltViewModel
class DbViewModel
@Inject
constructor(
    private val dbRepository: DbRepository
) : ViewModel() {

    val movies: LiveData<List<Movie>> = dbRepository.getMovies().asLiveData()
    val persons: LiveData<List<Person>> = dbRepository.getPersons().asLiveData()


    private fun insertPerson(person: Person) = viewModelScope.launch {
        dbRepository.insertPerson(person)
    }

    private fun deletePerson(person: Person) = viewModelScope.launch {
        dbRepository.deletePerson(person)
    }

    fun updatePerson(person: Person) = viewModelScope.launch {
        dbRepository.updatePerson(person)
    }

    private fun insertMovie(movie: Movie) = viewModelScope.launch {
        dbRepository.insertMovie(movie)
    }

    private fun deleteMovie(movie: Movie) = viewModelScope.launch {
        dbRepository.deleteMovie(movie)
    }

    fun updateMovie(movie: Movie) = viewModelScope.launch {
        dbRepository.updateMovie(movie)
    }

    /**
     * Check if the person is already on the list of favorites.
     * @return True if is my favorite person, False instead
     */
    fun isMyFavoritePerson(person: Person): Boolean = dbRepository.isPersonFavorite(person.id)

    /**
     * Check if the movie is already on the list of favorites.
     * @return True if is my favorite movie, False instead
     */
    fun isMyFavoriteMovie(movie: Movie): Boolean = dbRepository.isMovieFavorite(movie.id)

    /**
     * Check if the person to be included is already a favorite.
     * If true, the person is removed otherwise it is added to favorites.
     * @return True if is set as favorite, False if it was favorite ad is removed
     */
    fun setAsFavorite(person: Person): Boolean {
        return if (isMyFavoritePerson(person)) {
            deletePerson(person)
            false
        } else {
            insertPerson(person)
            true
        }
    }

    /**
     * Check if the person to be included is already a favorite.
     * If true, the person is removed otherwise it is added to favorites.
     * @return True if is set as favorite, False if it was favorite ad is removed
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
     * Sums the duration of all movies whose state is the one given as parameter and returns a string.
     * @return A string indicating the total number of hours.
     */
    fun getTotalHoursOf(status: PersonalStatus): String {
        var totalMinutes = 0
        movies.value?.forEach { movie ->
            if (movie.personalStatus == status && movie.runtime != null) {
                totalMinutes += movie.runtime
            }
        }
        return (totalMinutes / 60).toString()
    }

    /**
     * Sums the duration of all movies and returns a string.
     * @return A string indicating the total number of hours.
     */
    fun getTotalHours(): String {
        var totalMinutes = 0
        movies.value?.forEach { movie ->
            if ((movie.personalStatus == PersonalStatus.SEEN || movie.favorite) && movie.runtime != null) {
                totalMinutes += movie.runtime
            }
        }
        return (totalMinutes / 60).toString()
    }

    fun getMoviesWithStatus(status: PersonalStatus): MutableList<Movie> {
        return (movies.value?.filter { movie -> movie.personalStatus == status }?.toMutableList())
            ?: mutableListOf()
    }


}
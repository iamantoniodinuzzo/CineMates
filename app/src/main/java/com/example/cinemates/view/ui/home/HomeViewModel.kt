package com.example.cinemates.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import com.example.cinemates.model.TvShow
import com.example.cinemates.repository.ActorRepository
import com.example.cinemates.repository.MovieRepository
import com.example.cinemates.repository.TvShowRepository
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.TimeWindow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is attached to the [HomeFragment]
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
    private val actorRepository: ActorRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    private val _trendingMovies = MutableLiveData<List<Movie>>()
    val trendingMovies: LiveData<List<Movie>> get() = _trendingMovies

    private val _tvShowOnTheAir = MutableLiveData<List<TvShow>>()
    val tvShowOnTheAir: LiveData<List<TvShow>> get() = _tvShowOnTheAir

    private val _trendingTvShow = MutableLiveData<List<TvShow>>()
    val trendingTvShow: LiveData<List<TvShow>> get() = _trendingTvShow

    private val _trendingPerson = MutableLiveData<List<Person>>()
    val trendingPerson: LiveData<List<Person>> get() = _trendingPerson

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    private val _popularTvShow = MutableLiveData<List<TvShow>>()
    val popularTvShow: LiveData<List<TvShow>> get() = _popularTvShow

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>> get() = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>> get() = _upcomingMovies

    init {
        getTrendingMovies()
        getTrendingTvShow()
        getTrendingPerson()
        getPopularMovies()
        getPopularTvShow()
        getTopRatedMovies()
        getUpcomingMovies()
        getTvShowOnTheAir()
    }

    private fun getTvShowOnTheAir() = viewModelScope.launch {
        try {
            tvShowRepository.getOnTheAir().collectLatest { tvShow ->

                _tvShowOnTheAir.postValue(tvShow)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
    }

    private fun getPopularTvShow() = viewModelScope.launch {
        try {
            tvShowRepository.getPopularTvShow().collectLatest { tvShow ->

                _popularTvShow.postValue(tvShow)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
    }

    private fun getTrendingTvShow() = viewModelScope.launch {
        try {
            tvShowRepository.getTrendingTvShow(
                MediaType.TV.toString(),
                TimeWindow.WEEK.toString()
            ).collectLatest { tvShow ->

                _trendingTvShow.postValue(tvShow)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
    }

    private fun getTrendingMovies() = viewModelScope.launch {
        try {
            movieRepository.getTrendingMovies(
                MediaType.MOVIE.toString(),
                TimeWindow.WEEK.toString()
            ).collectLatest { movies ->

                _trendingMovies.postValue(movies)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

    private fun getTrendingPerson() = viewModelScope.launch {
        try {
            actorRepository.getTrendingPerson(
                MediaType.PERSON.toString(),
                TimeWindow.WEEK.toString()
            ).collectLatest { people ->
                _trendingPerson.postValue(people)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

    private fun getPopularMovies() = viewModelScope.launch {
        try {
            movieRepository.getPopularMovies().collectLatest { movies ->

                _popularMovies.postValue(movies)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        try {
            movieRepository.getTopRatedMovies().collectLatest { movies ->

                _topRatedMovies.postValue(movies)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        try {
            movieRepository.getUpcomingMovies().collectLatest { movies ->

                _upcomingMovies.postValue(movies)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

}


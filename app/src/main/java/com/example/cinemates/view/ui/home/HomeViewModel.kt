package com.example.cinemates.view.ui.home

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.example.cinemates.R
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import com.example.cinemates.model.TvShow
import com.example.cinemates.repository.ActorRepository
import com.example.cinemates.repository.MovieRepository
import com.example.cinemates.repository.TvShowRepository
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.TimeWindow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    private val tvShowRepository: TvShowRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    init {
        getPopularMovies()
        getUpcomingMovies()
        getTopRatedMovies()
    }

    private val _trendingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val trendingMovies: Flow<List<Movie>> get() = _trendingMovies

    private val _tvShowOnTheAir = MutableStateFlow<List<TvShow>>(emptyList())
    val tvShowOnTheAir: Flow<List<TvShow>> get() = _tvShowOnTheAir

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

    private val movieListSpecification: MutableStateFlow<String> =
        MutableStateFlow(MovieListSpecification.POPULAR.value)

    companion object {
        enum class MovieListSpecification(@StringRes val nameResource: Int, val value: String) {
            POPULAR(R.string.chip_popular, "popular"),
            TOP_RATED(R.string.chip_top_rated,"top_rated"),
            UPCOMING(R.string.chip_upcoming, "upcoming")
        }
    }

    val movieListBySpecification = combine(
        movieListSpecification
    ) { (query) ->
        MovieSpecificationParam(query)
    }.flatMapLatest {
        movieRepository.getSpecificMovieList(it.query)
    }

    //set query in SavedStateHandle
    fun setMovieListSpecification(query: MovieListSpecification) {
        state["query"] = query.value
        movieListSpecification.value =
            state.getLiveData("query", MovieListSpecification.POPULAR.value).value.toString()
    }

    fun getTvShowOnTheAir() = viewModelScope.launch {
        try {
            tvShowRepository.getOnTheAir().collectLatest { tvShow ->

                _tvShowOnTheAir.value = tvShow

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

                _trendingMovies.value = movies

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
            movieRepository.getSpecificMovieList(MovieListSpecification.POPULAR.value)
                .collectLatest { movies ->
                    _popularMovies.postValue(movies)

                }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        try {
            movieRepository.getSpecificMovieList(MovieListSpecification.TOP_RATED.value)
                .collectLatest { movies ->

                    _topRatedMovies.postValue(movies)

                }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        try {
            movieRepository.getSpecificMovieList(MovieListSpecification.UPCOMING.value)
                .collectLatest { movies ->

                    _upcomingMovies.postValue(movies)

                }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }

}

data class MovieSpecificationParam(
    val query: String
)


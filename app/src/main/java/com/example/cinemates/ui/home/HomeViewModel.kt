package com.example.cinemates.ui.home

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.R
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import com.example.cinemates.model.TvShow
import com.example.cinemates.data.remote.repository.ActorRepositoryImpl
import com.example.cinemates.data.remote.repository.MovieRepositoryImpl
import com.example.cinemates.data.remote.repository.TvShowRepositoryImpl
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.TimeWindow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
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
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val actorRepositoryImpl: ActorRepositoryImpl,
    private val tvShowRepositoryImpl: TvShowRepositoryImpl,
) : ViewModel() {

    init {
        fetchData()
    }

    private val _trendingMovies = MutableStateFlow<List<Movie>>(listOf())
    val trendingMovies: Flow<List<Movie>> get() = _trendingMovies

    private val _tvShowOnTheAir = MutableStateFlow<List<TvShow>>(listOf())
    val tvShowOnTheAir: Flow<List<TvShow>> get() = _tvShowOnTheAir


    private val _trendingTvShow = MutableStateFlow<List<TvShow>>(listOf())
    val trendingTvShow: Flow<List<TvShow>> get() = _trendingTvShow


    private val _trendingPerson = MutableStateFlow<List<Person>>(listOf())
    val trendingPerson: Flow<List<Person>> get() = _trendingPerson

    private val _popularMovies = MutableStateFlow<List<Movie>>(listOf())
    val popularMovies: Flow<List<Movie>> get() = _popularMovies

    private val _popularTvShow = MutableStateFlow<List<TvShow>>(listOf())
    val popularTvShow: Flow<List<TvShow>> get() = _popularTvShow


    private val _topRatedMovies = MutableStateFlow<List<Movie>>(listOf())
    val topRatedMovies: Flow<List<Movie>> get() = _topRatedMovies

    private val _upcomingMovies = MutableStateFlow<List<Movie>>(listOf())
    val upcomingMovies: Flow<List<Movie>> get() = _upcomingMovies


    companion object {
        enum class MovieListSpecification(@StringRes val nameResource: Int, val value: String) {
            POPULAR(R.string.chip_popular, "popular"),
            TOP_RATED(R.string.chip_top_rated, "top_rated"),
            UPCOMING(R.string.chip_upcoming, "upcoming"),
            ON_AIR(R.string.chip_on_air, "on_the_air")
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            withTimeout(1000) {
                launch {
                    movieRepositoryImpl.getSpecificMovieList(MovieListSpecification.POPULAR.value)
                        .collectLatest { popular ->
                            _popularMovies.value = popular
                        }
                }
                launch {
                    movieRepositoryImpl.getSpecificMovieList(MovieListSpecification.UPCOMING.value)
                        .collectLatest { upcoming ->
                            _upcomingMovies.value = upcoming
                        }
                }
                launch {
                    movieRepositoryImpl.getSpecificMovieList(MovieListSpecification.TOP_RATED.value)
                        .collectLatest { topRated ->
                            _topRatedMovies.value = topRated
                        }
                }
                launch {
                    movieRepositoryImpl.getTrending(TimeWindow.WEEK.value)
                        .collectLatest { trending ->
                            _trendingMovies.value = trending
                        }
                }
                launch {
                    tvShowRepositoryImpl.getTrending(TimeWindow.WEEK.value)
                        .collectLatest { trending ->
                            _trendingTvShow.value = trending
                        }
                }
                launch {
                    tvShowRepositoryImpl.getSpecificTVList(MovieListSpecification.POPULAR.value)
                        .collectLatest { popular ->
                            _popularTvShow.value = popular
                        }
                }
                launch {
                    tvShowRepositoryImpl.getSpecificTVList(MovieListSpecification.ON_AIR.value)
                        .collectLatest { onTheAir ->
                            _tvShowOnTheAir.value = onTheAir
                        }
                }
                launch {
                    actorRepositoryImpl.getTrendingPerson(MediaType.PERSON.value, TimeWindow.WEEK.value)
                        .collectLatest { trending ->
                            _trendingPerson.value = trending

                        }
                }
            }
        }
    }


}

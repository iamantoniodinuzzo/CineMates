package com.example.cinemates.ui.home

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.R
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import com.example.cinemates.model.TvShow
import com.example.cinemates.repositories.ActorRepository
import com.example.cinemates.repositories.MovieRepository
import com.example.cinemates.repositories.TvShowRepository
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.TimeWindow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val tvShowRepository: TvShowRepository,
) : ViewModel() {

    init {
        fetchData()
    }

    private val _trendingMovies = MutableStateFlow<List<Movie>?>(listOf())
    val trendingMovies: Flow<List<Movie>?> get() = _trendingMovies

    //    val trendingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _tvShowOnTheAir = MutableStateFlow<List<TvShow>?>(listOf())
    val tvShowOnTheAir: Flow<List<TvShow>?> get() = _tvShowOnTheAir

//    val tvShowOnTheAir: MutableLiveData<List<TvShow>> = MutableLiveData()

    private val _trendingTvShow = MutableStateFlow<List<TvShow>?>(listOf())
    val trendingTvShow: Flow<List<TvShow>?> get() = _trendingTvShow

//    val trendingTvShow: MutableLiveData<List<TvShow>> = MutableLiveData()

    private val _trendingPerson = MutableStateFlow<List<Person>?>(listOf())
    val trendingPerson: Flow<List<Person>?> get() = _trendingPerson
//    val trendingPerson: MutableLiveData<List<Person>> = MutableLiveData()

    private val _popularMovies = MutableStateFlow<List<Movie>?>(listOf())
    val popularMovies: Flow<List<Movie>?> get() = _popularMovies
//    val popularMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    private val _popularTvShow = MutableStateFlow<List<TvShow>?>(listOf())
    val popularTvShow: Flow<List<TvShow>?> get() = _popularTvShow

//    val popularTvShow: MutableLiveData<List<TvShow>> = MutableLiveData()

    private val _topRatedMovies = MutableStateFlow<List<Movie>?>(listOf())
    val topRatedMovies: Flow<List<Movie>?> get() = _topRatedMovies

//    val topRatedMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    private val _upcomingMovies = MutableStateFlow<List<Movie>?>(listOf())
    val upcomingMovies: Flow<List<Movie>?> get() = _upcomingMovies

//    val upcomingMovies: MutableLiveData<List<Movie>> = MutableLiveData()

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
            launch {
                movieRepository.getSpecificMovieList(MovieListSpecification.POPULAR.value)
                    .collectLatest { popular ->
                        _popularMovies.value = popular
                    }
            }
            launch {
                movieRepository.getSpecificMovieList(MovieListSpecification.UPCOMING.value)
                    .collectLatest { upcoming ->
                        _upcomingMovies.value = upcoming
                    }
            }
            launch {
                movieRepository.getSpecificMovieList(MovieListSpecification.TOP_RATED.value)
                    .collectLatest { topRated ->
                        _topRatedMovies.value = topRated
                    }
            }
            launch {
                movieRepository.getTrending(TimeWindow.WEEK.value)
                    .collectLatest { trending ->
                        _trendingMovies.value = trending
                    }
            }
            launch {
                tvShowRepository.getTrending(TimeWindow.WEEK.value)
                    .collectLatest { trending ->
                        _trendingTvShow.value = trending
                    }
            }
            launch {
                tvShowRepository.getSpecificTVList(MovieListSpecification.POPULAR.value)
                    .collectLatest { popular ->
                        _popularTvShow.value = popular
                    }
            }
            launch {
                tvShowRepository.getSpecificTVList(MovieListSpecification.ON_AIR.value)
                    .collectLatest { onTheAir ->
                        _tvShowOnTheAir.value = onTheAir
                    }
            }
            launch {
                actorRepository.getTrendingPerson(MediaType.PERSON.value, TimeWindow.WEEK.value)
                    .collectLatest { trending ->
                        _trendingPerson.value = trending

                    }
            }
        }
    }


}

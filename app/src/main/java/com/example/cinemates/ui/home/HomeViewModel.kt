package com.example.cinemates.ui.home

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
    private val state: SavedStateHandle
) : ViewModel() {

    init {
        fetchData()
    }

//TODO MutableStateFlow
    val trendingMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    val tvShowOnTheAir: MutableLiveData<List<TvShow>> = MutableLiveData()

    val trendingTvShow: MutableLiveData<List<TvShow>> = MutableLiveData()

    val trendingPerson: MutableLiveData<List<Person>> = MutableLiveData()

    val popularMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    val popularTvShow: MutableLiveData<List<TvShow>> = MutableLiveData()

    val topRatedMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    val upcomingMovies: MutableLiveData<List<Movie>> = MutableLiveData()

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
                        popularMovies.value = popular
                    }
            }
            launch {
                movieRepository.getSpecificMovieList(MovieListSpecification.UPCOMING.value)
                    .collectLatest { upcoming ->
                        upcomingMovies.value = upcoming
                    }
            }
            launch {
                movieRepository.getSpecificMovieList(MovieListSpecification.TOP_RATED.value)
                    .collectLatest { topRated ->
                        topRatedMovies.value = topRated
                    }
            }
            launch {
                movieRepository.getTrending(TimeWindow.WEEK.value)
                    .collectLatest { trending ->
                        trendingMovies.value = trending
                    }
            }
            launch {
                tvShowRepository.getTrending(TimeWindow.WEEK.value)
                    .collectLatest { trending ->
                        trendingTvShow.value = trending
                    }
            }
            launch {
                tvShowRepository.getSpecificTVList(MovieListSpecification.POPULAR.value)
                    .collectLatest { popular ->
                        popularTvShow.value = popular
                    }
            }
            launch {
                tvShowRepository.getSpecificTVList(MovieListSpecification.ON_AIR.value)
                    .collectLatest { onTheAir ->
                        tvShowOnTheAir.value = onTheAir
                    }
            }
            launch {
                actorRepository.getTrendingPerson(MediaType.PERSON.value, TimeWindow.WEEK.value)
                    .collectLatest { trending ->
                        trendingPerson.value = trending

                    }
            }
        }
    }


}

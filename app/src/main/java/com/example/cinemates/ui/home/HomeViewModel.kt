package com.example.cinemates.ui.home

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
        fetchData()
    }


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
            UPCOMING(R.string.chip_upcoming, "upcoming")
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            movieRepository.getSpecificMovieList(MovieListSpecification.POPULAR.value)
                .collectLatest { popular ->
                    popularMovies.value = popular
                }
            movieRepository.getSpecificMovieList(MovieListSpecification.UPCOMING.value)
                .collectLatest { upcoming ->
                    upcomingMovies.value = upcoming
                }
            movieRepository.getSpecificMovieList(MovieListSpecification.TOP_RATED.value)
                .collectLatest { topRated ->
                    topRatedMovies.value = topRated
                }
            movieRepository.getTrendingMovies(MediaType.MOVIE.value, TimeWindow.WEEK.value)
                .collectLatest { trending ->
                    trendingMovies.value = trending
                }
            tvShowRepository.getTrendingTvShow(MediaType.TV.value, TimeWindow.WEEK.value)
                .collectLatest { trending ->
                    trendingTvShow.value = trending
                }
            tvShowRepository.getPopularTvShow().collectLatest { popular ->
                popularTvShow.value = popular
            }
            tvShowRepository.getOnTheAir().collectLatest { onTheAir ->
                tvShowOnTheAir.value = onTheAir
            }
            actorRepository.getTrendingPerson(MediaType.PERSON.value, TimeWindow.WEEK.value)
                .collectLatest { trending ->
                    trendingPerson.value = trending

                }
        }
    }

    /* val movieListBySpecification = combine(
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
     }*/


}

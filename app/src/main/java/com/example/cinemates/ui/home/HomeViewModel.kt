package com.example.cinemates.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.domain.model.common.Media
import com.example.cinemates.domain.model.credits.Person
import com.example.cinemates.domain.usecases.home.HomeUseCaseContainer
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
    private val homeUseCaseContainer: HomeUseCaseContainer,
) : ViewModel() {

    private val _trendingActors = MutableStateFlow<List<Person>?>(null)
    val trendingActors: Flow<List<Person>?> get() = _trendingActors
    private val _tvSHowOnTheAir = MutableStateFlow<List<Media>?>(null)
    val tvSHowOnTheAir: Flow<List<Media>?> get() = _tvSHowOnTheAir
    private val _trendingTvShow = MutableStateFlow<List<Media>?>(null)
    val trendingTvShow: Flow<List<Media>?> get() = _trendingTvShow
    private val _popularTvShow = MutableStateFlow<List<Media>?>(null)
    val popularTvShow: Flow<List<Media>?> get() = _popularTvShow
    private val _upcomingMovies = MutableStateFlow<List<Media>?>(null)
    val upcomingMovies: Flow<List<Media>?> get() = _upcomingMovies
    private val _trendingMovies = MutableStateFlow<List<Media>?>(null)
    val trendingMovies: Flow<List<Media>?> get() = _trendingMovies
    private val _topRatedMovies = MutableStateFlow<List<Media>?>(null)
    val topRatedMovies: Flow<List<Media>?> get() = _topRatedMovies
    private val _popularMovies = MutableStateFlow<List<Media>?>(null)
    val popularMovies: Flow<List<Media>?> get() = _popularMovies


    init {
        fetchData()
    }

    private fun fetchData() {
        getPopularMovies()
        getTopRatedMovies()
        getTrendingMovies()
        getUpcomingMovies()
        getPopularTvShow()
        getTrendingTvShow()
        getOnTheAirTvShow()
        getTrendingActors()

    }

    private fun getTrendingActors() {
        viewModelScope.launch {
            homeUseCaseContainer.getTrendingActorsUseCase().collectLatest { list ->
                _trendingActors.value = list
            }
        }
    }


    private fun getOnTheAirTvShow() {
        viewModelScope.launch {
            homeUseCaseContainer.getTvShowOnTheAirUseCase().collectLatest { list ->
                _tvSHowOnTheAir.value = list
            }
        }
    }

    private fun getTrendingTvShow() {
        viewModelScope.launch {
            homeUseCaseContainer.getTrendingTvShowUseCase().collectLatest { list ->
                _trendingTvShow.value = list
            }
        }
    }

    private fun getPopularTvShow() {
        viewModelScope.launch {
            homeUseCaseContainer.getPopularTvShowUseCase().collectLatest { list ->
                _popularTvShow.value = list
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            homeUseCaseContainer.getUpcomingMoviesUseCase().collectLatest { list ->
                _upcomingMovies.value = list
            }
        }
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            homeUseCaseContainer.getTrendingMoviesUseCase().collectLatest { list ->
                _trendingMovies.value = list
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            homeUseCaseContainer.getTopRatedMoviesUseCase().collectLatest { list ->
                _topRatedMovies.value = list
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            homeUseCaseContainer.getPopularMoviesUseCase().collectLatest { list ->
                _popularMovies.value = list
            }
        }
    }


}

package com.indisparte.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.actor.repository.PeopleRepository
import com.indisparte.home.util.Section
import com.indisparte.model.entity.filter.TimeWindow
import com.indisparte.movie.repository.MovieRepository
import com.indisparte.movie.util.MovieListType
import com.indisparte.tv.repository.TvRepository
import com.indisparte.tv.util.TvListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val tvRepository: TvRepository,
    private val peopleRepository: PeopleRepository,
) : ViewModel() {


    private val _sections = MutableStateFlow<List<Section>>(emptyList())
    val sections: StateFlow<List<Section>> get() = _sections

    private val sectionMap = mutableMapOf<Int, Section>()

    init {
        fetchData()
    }


    private suspend fun fetchPopularMovies() {
        movieRepository.getByListType(MovieListType.POPULAR).collect { resource ->
                val movieSection = Section.MovieSection(R.string.section_popular_movie, resource)
                updateSection(movieSection)
        }
    }

    private suspend fun fetchTopRatedMovies() {
        movieRepository.getByListType(MovieListType.TOP_RATED).collect { resource ->
            val movieSection = Section.MovieSection(R.string.section_top_rated_movie, resource)
            updateSection(movieSection)
        }
    }

    private suspend fun fetchUpcomingMovies() {
        movieRepository.getByListType(MovieListType.UPCOMING).collect { resource ->
            val movieSection = Section.MovieSection(R.string.section_upcoming_movie, resource)
            updateSection(movieSection)
        }
    }

    private suspend fun fetchTrendingMovies() {
        movieRepository.getTrending(TimeWindow.WEEK).collect { resource ->
            val movieSection = Section.MovieSection(R.string.section_trending_movie, resource)
            updateSection(movieSection)
        }
    }

    private suspend fun fetchPopularTvShow() {
        tvRepository.getSpecificTVList(TvListType.POPULAR).collect { resource ->
            val tvSection = Section.TvShowSection(R.string.section_popular_tv, resource)
            updateSection(tvSection)
        }
    }

    private suspend fun fetchTrendingTvShow() {
        tvRepository.getTrending(TimeWindow.WEEK).collect { resource ->
            val tvSection = Section.TvShowSection(R.string.section_trending_tv, resource)
            updateSection(tvSection)
        }
    }

    private suspend fun fetchOnTheAirTvShow() {
        tvRepository.getSpecificTVList(TvListType.ON_THE_AIR).collect { resource ->
            val tvSection = Section.TvShowSection(R.string.section_onTheAir_tv, resource)
            updateSection(tvSection)
        }
    }

    private suspend fun fetchPopularPeople() {
        peopleRepository.getPopularPersons().collect { resource ->
            val peopleSection = Section.PeopleSection(R.string.section_popular_people, resource)
            updateSection(peopleSection)
        }
    }


    fun fetchData() {
        viewModelScope.launch {
            try {
                val popularMoviesDeferred = async { fetchPopularMovies() }
                val upcomingMoviesDeferred = async { fetchUpcomingMovies() }
                val trendingMoviesDeferred = async { fetchTrendingMovies() }
                val popularPeopleDeferred = async { fetchPopularPeople() }
                val popularTvShowDeferred = async { fetchPopularTvShow() }
                val onTheAirTvShowDeferred = async { fetchOnTheAirTvShow() }
                val trendingTvShowDeferred = async { fetchTrendingTvShow() }
                val topRatedMoviesDeferred = async { fetchTopRatedMovies() }

                awaitAll(
                    popularMoviesDeferred,
                    upcomingMoviesDeferred,
                    trendingMoviesDeferred,
                    popularPeopleDeferred,
                    popularTvShowDeferred,
                    onTheAirTvShowDeferred,
                    trendingTvShowDeferred,
                    topRatedMoviesDeferred
                )
            } catch (e: Exception) {
                // Manage here the general error
            }
        }
    }


    private fun updateSection(section: Section) {
        sectionMap[section.titleResId] = section
        _sections.value = sectionMap.values.toList()
    }

}

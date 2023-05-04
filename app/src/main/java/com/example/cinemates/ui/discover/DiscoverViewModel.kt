package com.example.cinemates.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemates.domain.model.common.MovieFilter
import com.example.cinemates.util.MovieSort
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 * Created 01/09/2022
 */
@HiltViewModel
class DiscoverViewModel
@Inject
constructor(

) : ViewModel() {


    private val m_Movie_filterBuilder = MutableLiveData<MovieFilter.Builder>()
    val movieFilterBuilder: LiveData<MovieFilter.Builder>
        get() = m_Movie_filterBuilder
    private val _genreMap = MutableLiveData<HashMap<Int, String>>()
    val genreMap: LiveData<HashMap<Int, String>> get() = _genreMap
    private val m_Movie_sortByMap = MutableLiveData<HashMap<MovieSort, String>>()
    val movieSortByMap: LiveData<HashMap<MovieSort, String>>
        get() = m_Movie_sortByMap


    init {
        initMaps()
        initFilter()
    }

    private fun initMaps() {
        _genreMap.value = getGenreMap()
        m_Movie_sortByMap.value = getSortByMap()
    }

    fun initFilter() {
        m_Movie_filterBuilder.value = MovieFilter.Builder()
    }

    private fun getGenreMap(): HashMap<Int, String> {
        val genreMap = HashMap<Int, String>()
        genreMap[28] = "Action"
        genreMap[12] = "Adventure"
        genreMap[16] = "Animation"
        genreMap[35] = "Comedy"
        genreMap[80] = "Crime"
        genreMap[99] = "Documentary"
        genreMap[18] = "Drama"
        genreMap[10751] = "Family"
        genreMap[14] = "Fantasy"
        genreMap[36] = "History"
        genreMap[27] = "Horror"
        genreMap[10402] = "Music"
        genreMap[9648] = "TopRated"
        genreMap[10749] = "Romance"
        genreMap[878] = "Science Fiction"
        genreMap[53] = "Thriller"
        genreMap[10752] = "War"
        genreMap[37] = "Western"
        genreMap[10770] = "TV MovieDetailsDTO"
        return genreMap
    }

    private fun getSortByMap(): HashMap<MovieSort, String> {
        val movieSortByMap = HashMap<MovieSort, String>()
        movieSortByMap[MovieSort.POPULARITY] = "Popularity"
        movieSortByMap[MovieSort.RELEASE_DATE] = "Release Date"
        movieSortByMap[MovieSort.REVENUE] = "Revenue"
        movieSortByMap[MovieSort.VOTE_AVERAGE] = "Vote Average"
        return movieSortByMap
    }


}
package com.example.cinemates.view.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemates.model.entities.Filter
import com.example.cinemates.util.Sort
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


private const val TAG = "DiscoverViewModel"

/**
 * @author Antonio Di Nuzzo
 * Created 01/09/2022
 */
@HiltViewModel
class DiscoverViewModel
@Inject
constructor(

) : ViewModel() {


    private val _filterBuilder = MutableLiveData<Filter.Builder>()
    val filterBuilder: LiveData<Filter.Builder>
        get() = _filterBuilder
    private val _genreMap = MutableLiveData<HashMap<Int, String>>()
    val genreMap: LiveData<HashMap<Int, String>> get() = _genreMap
    private val _sortByMap = MutableLiveData<HashMap<Sort, String>>()
    val sortByMap: LiveData<HashMap<Sort, String>>
        get() = _sortByMap


    init {
        initMaps()
        initFilter()
    }

    private fun initMaps() {
        _genreMap.value = getGenreMap()
        _sortByMap.value = getSortByMap()
    }

    fun initFilter() {
        _filterBuilder.value = Filter.Builder()
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
        genreMap[9648] = "Mystery"
        genreMap[10749] = "Romance"
        genreMap[878] = "Science Fiction"
        genreMap[53] = "Thriller"
        genreMap[10752] = "War"
        genreMap[37] = "Western"
        genreMap[10770] = "TV Movie"
        return genreMap
    }

    private fun getSortByMap(): HashMap<Sort, String> {
        val sortByMap = HashMap<Sort, String>()
        sortByMap[Sort.POPULARITY] = "Popularity"
        sortByMap[Sort.RELEASE_DATE] = "Release Date"
        sortByMap[Sort.REVENUE] = "Revenue"
        sortByMap[Sort.VOTE_AVERAGE] = "Vote Average"
        return sortByMap
    }


}
package com.example.cinemates.view.ui.discover

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemates.R
import com.example.cinemates.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject


private const val TAG = "DiscoverViewModel"

/**
 * @author Antonio Di Nuzzo
 * Created 01/09/2022
 */
@HiltViewModel
class DiscoverViewModel
@Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val mRnd = Random()
    private val _selectedGenres = MutableLiveData<String>()
    val selectedGenres: LiveData<String> get() = _selectedGenres
    private val _randomColor = MutableLiveData<Int>()
    val randomColor: LiveData<Int> get() = _randomColor
    private val _genreMap = MutableLiveData<HashMap<Int, String>>()
    val genreMap: LiveData<HashMap<Int, String>> get() = _genreMap


    init {
        initGenres()
        initRandomColor()
        initGenreMap()
    }

    private fun initGenreMap() {
        _genreMap.value = getGenreMap()
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

    private fun initRandomColor() {
        _randomColor.value = getRandomColor()
    }

    private fun getRandomColor(): Int {
        val baseColor = R.color.vermilion_100 //TODO maybe this color can be customizable
        val baseRed = Color.red(baseColor)
        val baseGreen = Color.green(baseColor)
        val baseBlue = Color.blue(baseColor)
        val red = (baseRed + mRnd.nextInt(256)) / 2
        val green = (baseGreen + mRnd.nextInt(256)) / 2
        val blue = (baseBlue + mRnd.nextInt(256)) / 2
        return Color.rgb(red, green, blue)
    }

    fun initGenres() {
        _selectedGenres.value = ""
    }

    /**
     * If the genre is not in the list it is added otherwise if already present it is removed
     */
    fun updateSelectedGenres(genreIds: String) {
        _selectedGenres.value = genreIds.replace("[", "").replace("]", "")

    }


}
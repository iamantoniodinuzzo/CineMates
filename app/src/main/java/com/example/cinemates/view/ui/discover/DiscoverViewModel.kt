package com.example.cinemates.view.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemates.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _selectedGenres = MutableLiveData<String>()
     val selectedGenres: LiveData<String> get() = _selectedGenres

    init {
        initGenres()
    }

     fun initGenres() {
        _selectedGenres.value = ""
    }

    /**
     * If the genre is not in the list it is added otherwise if already present it is removed
     */
    fun updateSelectedGenres(genreIds: String) {
       _selectedGenres.value = genreIds.replace("[","").replace("]","")

    }




}
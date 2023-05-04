package com.example.cinemates.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.domain.model.common.Genre
import com.example.cinemates.domain.usecases.discover.DiscoverUseCaseContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 * Created 01/09/2022
 */
@HiltViewModel
class DiscoverViewModel
@Inject
constructor(
    private val discoverUseCaseContainer: DiscoverUseCaseContainer,
) : ViewModel() {

    private val _movieGenres = MutableStateFlow<List<Genre>?>(null)
    val movieGenres: Flow<List<Genre>?> get() = _movieGenres
    private val _tvGenres = MutableStateFlow<List<Genre>?>(null)
    val tvGenres: Flow<List<Genre>?> get() = _tvGenres

    init {
        getMovieGenres()
        getTvGenres()
    }

    private fun getTvGenres() = viewModelScope.launch {
        discoverUseCaseContainer.getTvGenresUseCase.invoke().collectLatest {
            _tvGenres.value = it
        }
    }


    private fun getMovieGenres() =viewModelScope.launch {
        discoverUseCaseContainer.getMovieGenresUseCase.invoke().collectLatest {
            _movieGenres.value = it
        }
    }


}
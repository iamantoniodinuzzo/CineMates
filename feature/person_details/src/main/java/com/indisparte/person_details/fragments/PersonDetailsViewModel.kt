package com.indisparte.person_details.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.actor.repository.PeopleRepository
import com.indisparte.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel
@Inject
constructor(
    private val personDetailsRepository: PeopleRepository,
) : ViewModel() {
    private val LOG = Timber.tag(PersonDetailsViewModel::class.java.simpleName)
    private val _personDetails = MutableStateFlow<Result<com.indisparte.person.PersonDetails>>(Result.Loading)
    val personDetails: SharedFlow<Result<com.indisparte.person.PersonDetails>> get() = _personDetails.asSharedFlow()

    private val _movieCredits = MutableStateFlow<Result<List<com.indisparte.movie_data.MovieCredit>>>(Result.Loading)
    val movieCredits: StateFlow<Result<List<com.indisparte.movie_data.MovieCredit>>> get() = _movieCredits


    fun getPersonDetailsAndCredits(id: Int) {
        viewModelScope.launch {
            getPersonDetails(id)
            getMovieCredits(id)
        }
    }

    private suspend fun getPersonDetails(id: Int) {
        _personDetails.value = Result.Loading
        try {
            personDetailsRepository.getPersonDetails(id).collectLatest {
                _personDetails.value = it
            }
        } catch (e: Exception) {
            _personDetails.value = Result.Error(e)
        }
    }

    private suspend fun getMovieCredits(id: Int) {
        _movieCredits.value = Result.Loading
        try {
            personDetailsRepository.getMovieCredits(id).collectLatest {
                _movieCredits.value = it
            }
        } catch (e: Exception) {
            _movieCredits.value = Result.Error(e)
        }
    }


}
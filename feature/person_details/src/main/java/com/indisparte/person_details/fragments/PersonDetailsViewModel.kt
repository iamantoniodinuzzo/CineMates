package com.indisparte.person_details.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.actor.repository.PeopleRepository
import com.indisparte.movie_data.MovieCredit
import com.indisparte.network.util.Result
import com.indisparte.network.exception.CineMatesException
import com.indisparte.person.PersonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _personDetails = MutableSharedFlow<Result<PersonDetails>>()
    val personDetails: SharedFlow<Result<PersonDetails>> get() = _personDetails

    private val _movieCredits = MutableStateFlow<Result<List<MovieCredit>>>(Result.Loading)
    val movieCredits: StateFlow<Result<List<MovieCredit>>> get() = _movieCredits


    fun getPersonDetailsAndCredits(id: Int) {
        viewModelScope.launch {
            getPersonDetails(id)
            getMovieCredits(id)
        }
    }

    private suspend fun getPersonDetails(id: Int) {
        _personDetails.emit(Result.Loading)
        try {
            personDetailsRepository.getPersonDetailsAndUpdateWithLocalData(id).collectLatest {
                _personDetails.emit(it)
            }
        } catch (e: CineMatesException) {
            _personDetails.emit(Result.Error(e))
        }
    }

    private suspend fun getMovieCredits(id: Int) {
        _movieCredits.value = Result.Loading
        try {
            personDetailsRepository.getMovieCredits(id).collectLatest {
                _movieCredits.value = it
            }
        } catch (e: CineMatesException) {
            _movieCredits.value = Result.Error(e)
        }
    }

    fun setPersonAsFavorite(person: PersonDetails) {
        viewModelScope.launch {
            personDetailsRepository.setPersonAsFavorite(person).collectLatest { isFavorite ->
                LOG.d("${person.name} is now favorite?: $isFavorite")
                person.isFavorite = isFavorite
                _personDetails.emit(Result.Success(person))

            }
        }
    }

    fun removePersonAsFavorite(person: PersonDetails) {
        viewModelScope.launch {
            personDetailsRepository.removePersonAsFavorite(person).collectLatest { isFavorite ->
                LOG.d("${person.name} is removed from favorite? $isFavorite")
                person.isFavorite = !isFavorite
                _personDetails.emit(Result.Success(person))


            }
        }
    }


}
package com.indisparte.person_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.actor.repository.PeopleRepository
import com.indisparte.model.entity.PersonDetails
import com.indisparte.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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
    private val _personDetails = MutableStateFlow<Result<PersonDetails>>(Result.Loading)
    val personDetails: SharedFlow<Result<PersonDetails>> get() = _personDetails.asSharedFlow()

    fun getPersonDetailsById(id: Int) {
        viewModelScope.launch {
            _personDetails.value = Result.Loading
            try {
                personDetailsRepository.getPersonDetails(id).collectLatest {
                    _personDetails.value = it
                }
            } catch (e: Exception) {
                _personDetails.value = Result.Error(e)
            }
        }
    }


}
package com.example.cinemates.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Person
import com.example.cinemates.repository.DbPersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DBViewModel"

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:33
 */
@HiltViewModel
class DbPersonViewModel
@Inject
constructor(
    private val dbPersonRepository: DbPersonRepository
) : ViewModel() {

    val persons: LiveData<List<Person>> = dbPersonRepository.getPersons().asLiveData()


    private fun insertPerson(person: Person) = viewModelScope.launch {
        dbPersonRepository.insertPerson(person)
    }

    private fun deletePerson(person: Person) = viewModelScope.launch {
        dbPersonRepository.deletePerson(person)
    }

    fun updatePerson(person: Person) = viewModelScope.launch {
        dbPersonRepository.updatePerson(person)
    }


    /**
     * Check if the person is already on the list of favorites.
     * @return True if is my favorite person, False instead
     */
    fun isMyFavoritePerson(person: Person): Boolean = dbPersonRepository.isPersonFavorite(person.id)


    /**
     * Check if the person to be included is already a favorite.
     * If true, the person is removed otherwise it is added to favorites.
     * @return True if is set as favorite, False if it was favorite and is removed
     */
    fun setAsFavorite(person: Person): Boolean {
        return if (isMyFavoritePerson(person)) {
            deletePerson(person)
            false
        } else {
            insertPerson(person)
            true
        }
    }


}
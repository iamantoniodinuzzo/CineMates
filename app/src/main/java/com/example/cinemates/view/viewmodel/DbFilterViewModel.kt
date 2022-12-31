package com.example.cinemates.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Filter
import com.example.cinemates.repository.DbFilterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DbFilterViewModel
@Inject
constructor(
    private val dbFilterRepository: DbFilterRepository
) : ViewModel() {


    val filters: LiveData<List<Filter>> =dbFilterRepository.getFilters().asLiveData()

    fun insertFilter(filter: Filter) = viewModelScope.launch {
        dbFilterRepository.insertFilter(filter)
    }

    fun deleteFilter(filter: Filter) = viewModelScope.launch {
        dbFilterRepository.deleteFilter(filter)
    }


}
package com.example.cinemates.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Filter
import com.example.cinemates.model.repository.DbFilterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DbFilterViewModel
@Inject
constructor(
    private val dbFilterRepository: DbFilterRepository
) : ViewModel() {

    init {
        getAllFilters()
    }

    private val _filters = MutableLiveData<List<Filter>>()
    val filters: LiveData<List<Filter>> get() = _filters


    fun saveFilter(filter: Filter) {
        insertFilter(filter)
    }

    private fun insertFilter(filter: Filter) = viewModelScope.launch {
        dbFilterRepository.insertFilter(filter)
    }

    private fun deleteFilter(filter: Filter) = viewModelScope.launch {
        dbFilterRepository.deleteFilter(filter)
    }

    private fun getAllFilters() = viewModelScope.launch {
        dbFilterRepository.getFilters().mapLatest { filters ->
            _filters.value = filters
        }
    }
}
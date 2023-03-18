package com.example.cinemates.view.dbviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Filter
import com.example.cinemates.repository.FilterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DbFilterViewModel
@Inject
constructor(
    private val filterRepository: FilterRepository
) : ViewModel() {


    val filters: LiveData<List<Filter>> =filterRepository.getFilters().asLiveData()

    fun insertFilter(filter: Filter) = viewModelScope.launch {
        filterRepository.insertFilter(filter)
    }

    fun deleteFilter(filter: Filter) = viewModelScope.launch {
        filterRepository.deleteFilter(filter)
    }


}
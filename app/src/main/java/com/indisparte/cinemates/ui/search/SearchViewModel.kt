package com.indisparte.cinemates.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


/**
 * Shared between [SearchFragment]
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
@HiltViewModel
class SearchViewModel
@Inject
constructor(
) : ViewModel() {

    private val _query = MutableStateFlow<String?>(null)
    val query: Flow<String?> get() = _query

    private val _isGridLayout = MutableStateFlow<Boolean?>(true)
    val isGridLayout: Flow<Boolean?> get() = _isGridLayout

    init {
        clearQuery()
    }

    fun setQuery(query: String) {
        _query.value = query.trim()
    }

    fun setIsGridLayout(value: Boolean) {
        _isGridLayout.value = value
    }


    private fun clearQuery() {
        _query.value = ""

    }

}
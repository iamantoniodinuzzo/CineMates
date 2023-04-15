package com.example.cinemates.ui.search

import androidx.lifecycle.ViewModel
import com.example.cinemates.domain.usecases.search.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
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
    private val getSearchUseCase: GetSearchUseCase
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

    val searchedActors = query.flatMapLatest { query ->
        query?.let {
            if (it.isBlank())
                emptyFlow()
            else
                getSearchUseCase.getSearchedPerson(it)
        } ?: emptyFlow()
    }

    val searchedMovies = query.flatMapLatest { query ->
        query?.let {
            if (it.isBlank())
                emptyFlow()
            else
                getSearchUseCase.getSearchedMovies(it)
        } ?: emptyFlow()
    }

    val searchedTvShow = query.flatMapLatest { query ->
        query?.let {
            if (it.isBlank())
                emptyFlow()
            else
                getSearchUseCase.getSearchedTv(it)
        } ?: emptyFlow()

    }


    private fun clearQuery() {
        _query.value = ""

    }

}
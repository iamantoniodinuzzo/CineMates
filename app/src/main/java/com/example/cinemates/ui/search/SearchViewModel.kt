package com.example.cinemates.ui.search

import androidx.lifecycle.ViewModel
import com.example.cinemates.data.remote.repository.ActorRepositoryImpl
import com.example.cinemates.data.remote.repository.MovieRepositoryImpl
import com.example.cinemates.data.remote.repository.TvShowRepositoryImpl
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
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val actorRepositoryImpl: ActorRepositoryImpl,
    private val tvShowRepositoryImpl: TvShowRepositoryImpl,
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
                actorRepositoryImpl.getPeoplesBySearch(it)
        } ?: emptyFlow()
    }

    val searchedMovies = query.flatMapLatest { query ->
        query?.let {
            if (it.isBlank())
                emptyFlow()
            else
                movieRepositoryImpl.getBySearch(it)
        } ?: emptyFlow()
    }

    val searchedTvShow = query.flatMapLatest { query ->
        query?.let {
            if (it.isBlank())
                emptyFlow()
            else
                tvShowRepositoryImpl.getBySearch(it)
        } ?: emptyFlow()

    }


    fun clearQuery() {
        _query.value = ""

    }

}
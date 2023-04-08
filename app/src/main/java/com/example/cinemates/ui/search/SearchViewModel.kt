package com.example.cinemates.ui.search

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.repositories.ActorRepository
import com.example.cinemates.repositories.MovieRepository
import com.example.cinemates.repositories.TvShowRepository
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
    private val movieRepository: MovieRepository,
    private val actorRepository: ActorRepository,
    private val tvShowRepository: TvShowRepository,
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
                actorRepository.getPeoplesBySearch(it)
        } ?: emptyFlow()
    }

    val searchedMovies = query.flatMapLatest { query ->
        query?.let {
            if (it.isBlank())
                emptyFlow()
            else
                movieRepository.getBySearch(it)
        } ?: emptyFlow()
    }

    val searchedTvShow = query.flatMapLatest { query ->
        query?.let {
            if (it.isBlank())
                emptyFlow()
            else
                tvShowRepository.getBySearch(it)
        } ?: emptyFlow()

    }


    fun clearQuery() {
        _query.value = ""

    }

}
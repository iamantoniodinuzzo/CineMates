package com.indisparte.search.fragments

import androidx.lifecycle.ViewModel
import com.indisparte.search.repository.MovieSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val movieSearchRepository: MovieSearchRepository,
) : ViewModel() {

}
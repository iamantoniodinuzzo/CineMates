package com.example.cinemates.domain.usecases.search

import com.example.cinemates.domain.usecases.search.getSearched.GetSearchedMovies
import com.example.cinemates.domain.usecases.search.getSearched.GetSearchedPerson
import com.example.cinemates.domain.usecases.search.getSearched.GetSearchedTv
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetSearchUseCase
@Inject
constructor(
    val getSearchedMovies: GetSearchedMovies,
    val getSearchedPerson: GetSearchedPerson,
    val getSearchedTv: GetSearchedTv
)
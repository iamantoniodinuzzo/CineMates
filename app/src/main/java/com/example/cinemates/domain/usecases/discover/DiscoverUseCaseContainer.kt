package com.example.cinemates.domain.usecases.discover

import com.example.cinemates.domain.usecases.discover.geByDiscover.GetMovieByDiscover
import com.example.cinemates.domain.usecases.discover.geByDiscover.GetTvByDiscover
import com.example.cinemates.domain.usecases.discover.getGenres.GetMovieGenresUseCase
import com.example.cinemates.domain.usecases.discover.getGenres.GetTvGenresUseCase
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class DiscoverUseCaseContainer
@Inject
constructor(
    val getMovieGenresUseCase: GetMovieGenresUseCase,
    val getTvGenresUseCase: GetTvGenresUseCase,
    val getMovieByDiscover: GetMovieByDiscover,
    val getTvByDiscover: GetTvByDiscover
)
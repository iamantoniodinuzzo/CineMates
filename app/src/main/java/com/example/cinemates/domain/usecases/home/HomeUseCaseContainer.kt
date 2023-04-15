package com.example.cinemates.domain.usecases.home

import com.example.cinemates.domain.usecases.home.getData.GetTrendingPersonUseCase
import com.example.cinemates.domain.usecases.home.getData.movie.GetPopularMoviesUseCase
import com.example.cinemates.domain.usecases.home.getData.movie.GetTopRatedMoviesUseCase
import com.example.cinemates.domain.usecases.home.getData.movie.GetTrendingMoviesUseCase
import com.example.cinemates.domain.usecases.home.getData.movie.GetUpcomingMoviesUseCase
import com.example.cinemates.domain.usecases.home.getData.tv.GetPopularTvShowUseCase
import com.example.cinemates.domain.usecases.home.getData.tv.GetTrendingTvShowUseCase
import com.example.cinemates.domain.usecases.home.getData.tv.GetTvShowOnTheAirUseCase
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class HomeUseCaseContainer
@Inject
constructor(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    val getPopularTvShowUseCase: GetPopularTvShowUseCase,
    val getTrendingTvShowUseCase: GetTrendingTvShowUseCase,
    val getTvShowOnTheAirUseCase: GetTvShowOnTheAirUseCase,
    val getTrendingActorsUseCase: GetTrendingPersonUseCase,
)
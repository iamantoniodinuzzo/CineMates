package com.indisparte.watch_provider.repository

import com.indisparte.model.MediaType
import com.indisparte.model.entity.CountryResult
import com.indisparte.network.Resource
import com.indisparte.network.getListFromResponse
import com.indisparte.watch_provider.mapper.mapToCountryResult
import com.indisparte.watch_provider.source.WatchProviderDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class WatchProviderRepositoryImpl
@Inject
constructor(
    private val watchProviderDataSource: WatchProviderDataSource,
    private val queryMap: Map<String, String>,
) : WatchProviderRepository {
    override suspend fun getMovieWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Resource<List<CountryResult>>> =
        getListFromResponse(
            request = {
                watchProviderDataSource.getWatchProviders(
                    movieId,
                    MediaType.MOVIE.value,
                    queryMap
                )
            },
            mapper = { response ->
                response.getCountryResultByCountry(country).map { it.mapToCountryResult() }
            }
        )

    override suspend fun getTvWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Resource<List<CountryResult>>> =
        getListFromResponse(
            request = {
                watchProviderDataSource.getWatchProviders(
                    movieId,
                    MediaType.TV.value,
                    queryMap
                )
            },
            mapper = { response ->
                response.getCountryResultByCountry(country).map { it.mapToCountryResult() }
            }
        )

}
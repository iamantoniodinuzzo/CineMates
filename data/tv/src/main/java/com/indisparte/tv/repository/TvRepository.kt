package com.indisparte.tv.repository

import android.media.Image
import android.provider.MediaStore
import com.indisparte.model.MediaFilter
import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Genre
import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.CountryResult
import com.indisparte.model.entity.EpisodeGroup
import com.indisparte.model.entity.EpisodeGroupDetails
import com.indisparte.model.entity.SeasonDetails
import com.indisparte.model.entity.TvShow
import com.indisparte.model.entity.TvShowDetails
import com.indisparte.network.Resource
import com.indisparte.tv.util.TvListType
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface TvRepository {
    suspend fun getSpecificTVList(tvListType: TvListType): Flow<Resource<List<TvShow>>>
    suspend fun getTrending(timeWindow: TimeWindow): Flow<Resource<List<TvShow>>>
    suspend fun getDetails(id: Int): Flow<Resource<TvShowDetails>>
    suspend fun getSimilar(id: Int): Flow<Resource<List<TvShow>>>
    suspend fun getDiscoverable(tvFilter: MediaFilter): Flow<Resource<List<TvShow>>>
    /*suspend fun getPosters(id: Int): Flow<Resource<List<Image>>>
    suspend fun getBackdrops(id: Int): Flow<Resource<List<Image>>>*/
    suspend fun getCast(id: Int): Flow<Resource<List<Cast>>>
    suspend fun getCrew(id: Int): Flow<Resource<List<Crew>>>
    suspend fun getBySearch(query: String): Flow<Resource<List<TvShow>>>
    suspend fun getEpisodeGroup(id: Int): Flow<Resource<List<EpisodeGroup>>>
    suspend fun getEpisodeGroupDetails(episodeGroupId: String): Flow<Resource<EpisodeGroupDetails>>
    suspend fun getSeasonDetails(tvId:Int, seasonNumber:Int): Flow<Resource<SeasonDetails>>
    suspend fun getWatchProviders(
        tvId: Int,
        country: String,
    ): Flow<Resource<List<CountryResult>>>
}
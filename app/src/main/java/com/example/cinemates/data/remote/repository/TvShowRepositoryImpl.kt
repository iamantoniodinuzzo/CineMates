package com.example.cinemates.data.remote.repository

import com.example.cinemates.data.remote.response.genre.GenreDTO
import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.data.remote.response.tvShow.*
import com.example.cinemates.data.remote.service.TvShowService
import com.example.cinemates.domain.model.common.MediaFilter
import com.example.cinemates.util.MediaListSpecification
import com.example.cinemates.util.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 */
class TvShowRepositoryImpl
@Inject
constructor(
    private val tvShowService: TvShowService,
    private val queryMap: MutableMap<String, String>
) : TvShowRepository {

    override fun getSpecificTVList(specification: MediaListSpecification) = flow {
        val specificTvShowList =
            tvShowService.getListOfSpecificTv(specification.value, queryMap).results
        emit(specificTvShowList)
    }

    override fun getTrending(timeWindow: TimeWindow): Flow<List<TvShowDTO>> =
        flow {
            val trending = tvShowService.getTrending(timeWindow.value, queryMap).results
            emit(trending)
        }


    override fun getVideos(id: Int): Flow<List<VideoDTO>> = flow {
        val videos = tvShowService.getVideos(id, queryMap).results
        emit(videos)
    }

    override fun getDetails(id: Int): Flow<TvShowDetailsDTO> = flow {
        val tvShowDetails = tvShowService.getDetails(id, queryMap)
        emit(tvShowDetails)
    }


    override fun getSimilar(id: Int): Flow<List<TvShowDTO>> = flow {
        val similarTvShow = tvShowService.getSimilar(id, queryMap).results
        emit(similarTvShow)
    }

    override fun getDiscoverable(tvFilter: MediaFilter): Flow<List<TvShowDTO>> = flow {
        tvFilter.sortBy?.let {
            queryMap["sort_by"] = it.toString()
        }
        tvFilter.genresId?.let {
            queryMap["with_genres"] =
                it.replace("[", "")//todo necessary?
                    .replace("]", "")//todo necessary?
        }
        tvFilter.year?.let {
            queryMap["first_air_date_year"] = it.toString()
        }

        val tv = tvShowService.getByDiscover(queryMap).results
        emit(tv)
    }

    override fun getPosters(id: Int): Flow<List<ImageDTO>> = flow {
        val posters = tvShowService.getImages(id, queryMap).posters
        emit(posters)
    }

    override fun getBackdrops(id: Int): Flow<List<ImageDTO>> = flow {
        val backdrops = tvShowService.getImages(id, queryMap).backdrops
        emit(backdrops)
    }

    override fun getCast(id: Int): Flow<List<CastDTO>> = flow {
        val cast = tvShowService.getCredits(id, queryMap).cast
        emit(cast)
    }

    override fun getCrew(id: Int): Flow<List<CrewDTO>> = flow {
        val crews = tvShowService.getCredits(id, queryMap).crew
        emit(crews)
    }


    override fun getBySearch(query: String): Flow<List<TvShowDTO>> = flow {
        queryMap["query"] = query
        emit(tvShowService.getBySearch(queryMap).results)
    }

    override fun getEpisodeGroup(id: Int): Flow<List<EpisodeGroupDTO>> = flow {
        val episodesGroup = tvShowService.getEpisodesGroup(id, queryMap).results
        emit(episodesGroup)
    }

    override fun getEpisodeGroupDetails(episodeGroupId: String): Flow<EpisodeGroupDetailsDTO> =
        flow {
            val episodesGroupDetails =
                tvShowService.getEpisodeGroupDetails(episodeGroupId, queryMap)
            emit(episodesGroupDetails)
        }

    override fun getSeasonDetails(tvId: Int, seasonNumber: Int): Flow<SeasonDetailsDTO> = flow {
        emit(tvShowService.getSeasonDetails(tvId, seasonNumber, queryMap))
    }

    override fun getGenreList(): Flow<List<GenreDTO>> = flow {
        val genres = tvShowService.getGenreList(queryMap).genres
        emit(genres)
    }

}


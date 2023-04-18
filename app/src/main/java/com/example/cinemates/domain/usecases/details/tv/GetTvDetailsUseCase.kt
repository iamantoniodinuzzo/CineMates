package com.example.cinemates.domain.usecases.details.tv

import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.credits.mapToCast
import com.example.cinemates.domain.mapper.credits.mapToCrew
import com.example.cinemates.domain.mapper.image.mapToImage
import com.example.cinemates.domain.mapper.trailer.mapToVideo
import com.example.cinemates.domain.mapper.tv.mapToEpisodeGroup
import com.example.cinemates.domain.mapper.tv.mapToEpisodeGroupDetails
import com.example.cinemates.domain.mapper.tv.mapToMedia
import com.example.cinemates.domain.mapper.tv.mapToTvShow
import com.example.cinemates.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetTvDetailsUseCase
@Inject
constructor(
    private val tvShowRepository: TvShowRepository,
) {
    fun getTvDetails(tvId: Int): Flow<TvShow> {
        return tvShowRepository.getDetails(tvId).map { it.mapToTvShow() }
    }

    fun getTvCast(tvId: Int): Flow<List<Cast>> {
        return tvShowRepository.getCast(tvId).map { castDTOList ->
            castDTOList.map { it.mapToCast() }
        }
    }

    fun getTvCrew(tvId: Int): Flow<List<Crew>> {
        return tvShowRepository.getCrew(tvId).map { crewDTOList ->
            crewDTOList.map { it.mapToCrew() }
        }
    }

    fun getSimilarTvs(tvId: Int): Flow<List<Media>> {
        return tvShowRepository.getSimilar(tvId).map { tvShowDTOList ->
            tvShowDTOList.map { it.mapToMedia() }
        }
    }

    fun getPosters(tvId: Int): Flow<List<Image>> {
        return tvShowRepository.getPosters(tvId).map { imageDTOList ->
            imageDTOList.map { it.mapToImage() }
        }
    }

    fun getBackdrops(tvId: Int): Flow<List<Image>> {
        return tvShowRepository.getBackdrops(tvId).map { imageDTOList ->
            imageDTOList.map { it.mapToImage() }
        }
    }

    fun getTrailers(tvId: Int): Flow<List<Video>> {
        return tvShowRepository.getVideos(tvId).map { videoDTOList ->
            videoDTOList.map { it.mapToVideo() }
        }
    }

    fun getEpisodeGroups(tvId: Int): Flow<List<EpisodeGroup>> {
        return tvShowRepository.getEpisodeGroup(tvId).map { episodeGroupDTOList ->
            episodeGroupDTOList.map { it.mapToEpisodeGroup() }
        }
    }

    fun getEpisodeGroupDetails(episodeGroupId: String): Flow<EpisodeGroupDetails> {
        return tvShowRepository.getEpisodeGroupDetails(episodeGroupId).map {
            it.mapToEpisodeGroupDetails()
        }
    }


}
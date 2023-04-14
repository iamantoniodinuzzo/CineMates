package com.example.cinemates.domain.usecases.details.tv

import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.credits.CastMapper
import com.example.cinemates.domain.mapper.credits.CrewMapper
import com.example.cinemates.domain.mapper.image.ImageMapper
import com.example.cinemates.domain.mapper.trailer.TrailerMapper
import com.example.cinemates.domain.mapper.tv.CreatedByToPersonMapper
import com.example.cinemates.domain.mapper.tv.TvDetailsMapper
import com.example.cinemates.domain.mapper.tv.TvToMediaMapper
import com.example.cinemates.domain.model.*
import com.example.cinemates.domain.model.Collection
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
    private val tvToMediaMapper: TvToMediaMapper,
    private val tvDetailsMapper: TvDetailsMapper,
    private val castMapper: CastMapper,
    private val crewMapper: CrewMapper,
    private val imageMapper: ImageMapper,
    private val trailerMapper: TrailerMapper,
) {
    fun getTvDetails(tvId: Int): Flow<TvShow> {
        return tvShowRepository.getDetails(tvId).map(tvDetailsMapper::map)
    }

    fun getTvCast(tvId: Int): Flow<List<Cast>> {
        return tvShowRepository.getCast(tvId).map {
            it.map(castMapper::map)
        }
    }

    fun getTvCrew(tvId: Int): Flow<List<Crew>> {
        return tvShowRepository.getCrew(tvId).map {
            it.map(crewMapper::map)
        }
    }

    fun getSimilarTvs(tvId: Int): Flow<List<Media>> {
        return tvShowRepository.getSimilar(tvId).map {
            it.map(tvToMediaMapper::map)
        }
    }

    fun getPosters(tvId: Int): Flow<List<Image>> {
        return tvShowRepository.getPosters(tvId).map {
            it.map(imageMapper::map)
        }
    }

    fun getBackdrops(tvId: Int): Flow<List<Image>> {
        return tvShowRepository.getBackdrops(tvId).map {
            it.map(imageMapper::map)
        }
    }

    fun getTrailers(tvId: Int): Flow<List<Video>> {
        return tvShowRepository.getVideos(tvId).map {
            it.map(trailerMapper::map)
        }
    }


}
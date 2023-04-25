package com.example.cinemates.domain.usecases.details.person

import com.example.cinemates.data.remote.repository.ActorRepository
import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.credits.mapToPersonDetails
import com.example.cinemates.domain.mapper.image.mapToImage
import com.example.cinemates.domain.mapper.movie.mapToMedia
import com.example.cinemates.domain.model.Image
import com.example.cinemates.domain.model.Media
import com.example.cinemates.domain.model.PersonDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetPersonDetailsUseCase
@Inject
constructor(
    private val actorRepository: ActorRepository,
    private val movieRepository: MovieRepository,
) {
    fun getPersonDetails(id: Int): Flow<PersonDetails> {
        return actorRepository.getActorDetails(id).map { it.mapToPersonDetails() }
    }

    fun getPersonMovies(id: Int): Flow<List<Media>> {
        return movieRepository.getMoviesByActor(id).map { movieDTOList ->
            movieDTOList.map { it.mapToMedia() }
        }
    }

    fun getPersonImages(id: Int): Flow<List<Image>> {
        return actorRepository.getImages(id).map { imageDTOList ->
            imageDTOList.map { it.mapToImage() }
        }
    }

}
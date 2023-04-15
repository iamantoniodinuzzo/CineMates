package com.example.cinemates.domain.usecases.details.person

import com.example.cinemates.data.remote.repository.ActorRepository
import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.credits.PersonDetailsMapper
import com.example.cinemates.domain.mapper.image.ImageMapper
import com.example.cinemates.domain.mapper.movie.MovieToMediaMapper
import com.example.cinemates.domain.model.*
import com.example.cinemates.domain.model.Collection
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
    private val movieToMediaMapper: MovieToMediaMapper,
    private val personDetailsMapper: PersonDetailsMapper,
    private val imageMapper: ImageMapper
) {
    fun getPersonDetails(id: Int): Flow<PersonDetails> {
        return actorRepository.getActorDetails(id).map(personDetailsMapper::map)
    }

    fun getPersonMovies(id: Int): Flow<List<Media>> {
        return movieRepository.getMoviesByActor(id).map {
            it.map(movieToMediaMapper::map)
        }
    }

    fun getPersonImages(id: Int): Flow<List<Image>> {
        return actorRepository.getImages(id).map {
            it.map(imageMapper::map)
        }
    }

}
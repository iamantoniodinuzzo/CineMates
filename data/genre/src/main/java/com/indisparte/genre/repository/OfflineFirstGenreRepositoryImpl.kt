package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.genre.source.local.GenreLocalDataSource
import com.indisparte.genre.source.remote.GenreRemoteDataSource
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class OfflineFirstGenreRepositoryImpl
@Inject
constructor(
    private val genreLocalDataSource: GenreLocalDataSource,
    private val genreRemoteDataSource: GenreRemoteDataSource,
) : GenreRepository {

    // TODO: Generalize with an inline func this SSOT 
    override fun getMovieGenreList(): Flow<Result<List<Genre>>> = flow {
        // Prima di accedere al database, controlla se ci sono dati salvati
        val localGenres = genreLocalDataSource.getAllSavedGenres().firstOrNull()

        if (!localGenres.isNullOrEmpty()) {
            // Se ci sono dati nel database locale, emettili direttamente
            Timber.tag("GenreRepository").d("Emit genre from DB")
            emit(Result.Success(localGenres))
        } else {
            // Se non ci sono dati nel database locale, recuperali dall'API
            genreRemoteDataSource.getMovieGenreList().collect { apiResult ->
                when (apiResult) {
                    is Result.Success -> {
                        // Salva i dati nell'API nel database locale
                        genreLocalDataSource.insertGenres(apiResult.data)
                        Timber.tag("GenreRepository").d("Emit genre from API")
                        emit(Result.Success(apiResult.data))
                    }

                    is Result.Error -> {
                        // Se si verifica un errore nell'API, emetti un errore
                        emit(Result.Error(apiResult.exception))
                    }

                    is Result.Loading -> {
                        // Il caso di caricamento dovrebbe essere già gestito dal tuo repository
                        // se vuoi supportare il caricamento graduale.
                        emit(Result.Loading)
                    }
                }
            }


        }
    }


    override fun getTvGenreList(): Flow<Result<List<Genre>>> = flow {
        emit(Result.Success(emptyList()))
    }

    override fun updateSavedGenre(genre: Genre) =
        genreLocalDataSource.updateGenre(genre)


    override fun getGenresByIds(genresId: List<Int>): Flow<List<Genre>> =
        genreLocalDataSource.getAllGenresById(genresId)
}
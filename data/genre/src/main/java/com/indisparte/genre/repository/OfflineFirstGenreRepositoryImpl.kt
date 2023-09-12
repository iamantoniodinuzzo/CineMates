package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.database.model.asDomain
import com.indisparte.genre.source.local.GenreLocalDataSource
import com.indisparte.genre.source.remote.GenreRemoteDataSource
import com.indisparte.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override fun getMovieGenreList(): Flow<Result<List<Genre>>> = flow {
        // Prima di accedere al database, controlla se ci sono dati salvati
        val localGenres = genreLocalDataSource.getAllSavedGenres().firstOrNull()

        if (!localGenres.isNullOrEmpty()) {
            // Se ci sono dati nel database locale, emettili direttamente
            Timber.tag("GenreRepository").d("Emit genres from db")
            emit(Result.Success(localGenres.map { it.asDomain() }))
        } else {
            // Se non ci sono dati nel database locale, recuperali dall'API
            genreRemoteDataSource.getMovieGenreList().collect { apiResult ->
                when (apiResult) {
                    is Result.Success -> {
                        // Salva i dati nell'API nel database locale
                        genreLocalDataSource.insertGenres(apiResult.data)
                        Timber.tag("GenreRepository").d("Emit genres from API")
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
    }.flowOn(Dispatchers.IO)


    override fun getTvGenreList(): Flow<Result<List<Genre>>> = flow {
        /*// 1. Emetti subito il valore in cache dalla fonte locale
        emit(Result.Loading)
        val localGenres = genreLocalDataSource.getAllSavedGenres().map { it.asDomain() }
        emit(Result.Success(localGenres))

        // 2. Esegui la richiesta alla fonte remota
        val remoteResult = genreRemoteDataSource.getTvGenreList().first()

        // 3. Se la richiesta remota ha successo, aggiorna la fonte locale
        if (remoteResult is Result.Success) {
            genreLocalDataSource.insertGenres(remoteResult.data)
        }

        // 4. Emetti il risultato della fonte remota
        emit(remoteResult)*/
        emit(Result.Success(emptyList()))
    }
}
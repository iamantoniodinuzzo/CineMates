package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeGenreRepository : GenreRepository {
    private val movieGenres = mutableListOf<Genre>()
    private val tvGenres = mutableListOf<Genre>()

    override fun getMovieGenreList(): Flow<Result<List<Genre>>> {
        // Implement the behavior to return fake data for getMovieGenreList
        return flow { emit(Result.Success(movieGenres)) }
    }

    override fun getTvGenreList(): Flow<Result<List<Genre>>> {
        // Implement the behavior to return fake data for getTvGenreList
        return flow { emit(Result.Success(tvGenres)) }
    }

    // Helper methods to set fake data in the fake repository
    fun addMovieGenres(genres: List<Genre>) {
        movieGenres.addAll(genres)
    }

    fun addTvGenres(genres: List<Genre>) {
        tvGenres.addAll(genres)
    }

    // Helper method to clear all fake data
    fun clearData() {
        movieGenres.clear()
        tvGenres.clear()
    }
}

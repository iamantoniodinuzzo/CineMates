package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.network.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 */
interface GenreRepository {
    /**
     * Retrieves a list of movie genres. This function checks if the data is available locally.
     * If local data is found, it returns that data. If not, it fetches the data from a remote source.
     * The fetched data is then saved locally with the media type set to 'Movie', and the retrieved
     * data, whether from the local or remote source, is returned as a [Flow] of [Result].
     *
     * @return A [Flow] of [Result] containing the list of movie genres.
     * @author Antonio Di Nuzzo
     */
    fun getMovieGenreList(): Flow<Result<List<Genre>>>

    /**
     * Retrieves a list of TV show genres. This function checks if the data is available locally.
     * If local data is found, it returns that data. If not, it fetches the data from a remote source.
     * The fetched data is then saved locally with the media type set to 'TV', and the retrieved
     * data, whether from the local or remote source, is returned as a [Flow] of [Result].
     *
     * @return A [Flow] of [Result] containing the list of TV show genres.
     */
    fun getTvGenreList(): Flow<Result<List<Genre>>>
    fun getAllGenres(): Flow<List<Genre>>

    /**
     * Retrieves a list of genres based on their unique identifiers [genresId] from the local data source.
     * This function returns a Flow emitting the list of genres matching the provided IDs.
     *
     * @param genresId The list of unique identifiers for genres to retrieve.
     *
     * @return A Flow emitting a list of genres that match the provided IDs. The emitted list may be
     * empty if no genres were found for the given IDs.
     */
    fun getGenresByIds(genresId: List<Int>): Flow<List<Genre>>

    /**
     * Updates a saved genre in the local data source. This function updates the provided [genre] in
     * the local data source and emits the result as a Flow of an integer value.
     *
     * @param genre The genre to be updated in the local data source.
     *
     * @return A Flow emitting the result of the update operation. The emitted value represents the
     * number of rows affected by the update. A value greater than 0 indicates a successful update,
     * while 0 indicates that no rows were updated.
     */
    fun updateSavedGenre(genre: Genre): Flow<Int>

    /**
     * Retrieves a list of favorite genres stored in the local data source.
     * This function returns a Flow emitting the list of favorite genres.
     *
     * @return A Flow emitting a list of favorite genres. The emitted list may be empty if no favorite
     * genres are found.
     */
    fun getMyFavGenres(): Flow<List<Genre>>

    /**
     * Fetches and stores all genres. This function first attempts to retrieve movie and TV show
     * genres from the local data source. If either of them is not available locally, it fetches
     * both sets of genres from a remote data source. The fetched data is then stored locally with
     * appropriate media types ('Movie', 'TV', or 'Both'), and common genres are identified and
     * stored as 'Both' media type genres. Genres that exist in both movie and TV genres are
     * considered common.
     *
     * @note If genres are not available locally or if there are changes in remote genres, this
     * function fetches and updates the local genres accordingly.
     * @author Antonio Di Nuzzo
     */
    suspend fun fetchAllGenres()
}
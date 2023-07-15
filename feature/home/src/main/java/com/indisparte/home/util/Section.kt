package com.indisparte.home.util

import androidx.annotation.StringRes
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.TvShow
import com.indisparte.network.Resource


/**
 * Sealed class representing a section.
 *
 * @param titleResId The string resource ID for the section title.
 */
sealed class Section(@StringRes val titleResId: Int) {
    /**
     * Subclass representing a movie section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param movies The resource representing the list of movies.
     */
    class MovieSection(@StringRes titleResId: Int, val movies: Resource<List<Movie>>?) :
        Section(titleResId)

    /**
     * Subclass representing a TV show section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param tvShows The resource representing the list of TV shows.
     */
    class TvShowSection(@StringRes titleResId: Int, val tvShows: Resource<List<TvShow>>?) :
        Section(titleResId)

    /**
     * Subclass representing a people section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param people The resource representing the list of people.
     */
    class PeopleSection(@StringRes titleResId: Int, val people: Resource<List<Person>>?) :
        Section(titleResId)
}

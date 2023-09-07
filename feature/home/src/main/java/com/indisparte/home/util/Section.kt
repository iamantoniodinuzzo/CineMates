package com.indisparte.home.util

import androidx.annotation.StringRes
import com.indisparte.network.Result


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
     * @param moviesResult The resource representing the list of movies.
     */
    class MovieSection(
        @StringRes titleResId: Int,
        val moviesResult: Result<List<com.indisparte.movie_data.Movie>>,
    ) :
        Section(titleResId) {
        override fun toString(): String {
            return "MovieSection(moviesResult=$moviesResult)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is MovieSection) return false

            if (moviesResult != other.moviesResult) return false

            return true
        }

        override fun hashCode(): Int {
            return moviesResult.hashCode()
        }


    }

    /**
     * Subclass representing a TV show section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param tvShowsResult The resource representing the list of TV shows.
     */
    class TvShowSection(
        @StringRes titleResId: Int,
        val tvShowsResult: Result<List<com.indisparte.tv.TvShow>>,
    ) :
        Section(titleResId) {
        override fun toString(): String {
            return "TvShowSection(tvShowsResult=$tvShowsResult)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is TvShowSection) return false

            if (tvShowsResult != other.tvShowsResult) return false

            return true
        }

        override fun hashCode(): Int {
            return tvShowsResult.hashCode()
        }


    }

    /**
     * Subclass representing a people section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param peopleResult The resource representing the list of people.
     */
    class PeopleSection(
        @StringRes titleResId: Int,
        val peopleResult: Result<List<com.indisparte.person.Person>>,
    ) :
        Section(titleResId) {
        override fun toString(): String {
            return "PeopleSection(peopleResult=$peopleResult)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PeopleSection) return false

            if (peopleResult != other.peopleResult) return false

            return true
        }

        override fun hashCode(): Int {
            return peopleResult.hashCode()
        }


    }


}

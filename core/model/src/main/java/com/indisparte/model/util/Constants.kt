package com.indisparte.model.util

/**
 * @author Antonio Di Nuzzo
 */
object Constants {
    /**
     * This constant stores the base URL for retrieving images with a width of 780 pixels from the TMDB (The Movie Database) API.
     */
    const val IMAGE_BASE_URL_W780 = "https://image.tmdb.org/t/p/w780"

    /**
     * This constant stores the base URL for retrieving images with a width of 500 pixels from the TMDB API.
     */
    const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"

    /**
     *  This constant specifies the date and time format used by the TMDB API, which is "yyyy-MM-dd"
     */
    const val TMDB_DATE_TIME_FORMAT = "yyyy-MM-dd"

    /**
     * This constant specifies an output date and time format used for displaying dates, which is "dd MMMM yyyy"
     */
    const val OUTPUT_DATE_TIME_FORMAT = "dd MMMM yyyy"

}
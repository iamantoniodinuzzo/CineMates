package com.example.cinemates.domain.model.section

import com.example.cinemates.model.Movie

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionMovie(title: String, movies: List<Movie>) : Section<Movie>(title, movies)

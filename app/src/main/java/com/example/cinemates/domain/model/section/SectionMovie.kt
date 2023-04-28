package com.example.cinemates.domain.model.section

import com.example.cinemates.domain.model.common.Media

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionMovie(title: String, movies: List<Media>) : Section<Media>(title, movies)

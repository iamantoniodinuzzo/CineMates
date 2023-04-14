package com.example.cinemates.domain.model.section

import com.example.cinemates.model.Episode
import com.example.cinemates.model.Movie

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionEpisodesGroup(title: String, movies: List<Episode>) : Section<Episode>(title, movies)

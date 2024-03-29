package com.example.cinemates.domain.model.section

import com.example.cinemates.domain.model.tv.Episode

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionEpisodesGroup(title: String, movies: List<Episode>) : Section<Episode>(title, movies)

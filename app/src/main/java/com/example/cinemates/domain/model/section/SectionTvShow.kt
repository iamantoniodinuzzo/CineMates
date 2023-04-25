package com.example.cinemates.domain.model.section

import com.example.cinemates.domain.model.Media


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionTvShow(title: String, tvShow: List<Media>) : Section<Media>(title, tvShow)

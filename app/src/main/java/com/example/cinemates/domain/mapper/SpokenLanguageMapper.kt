package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.common.SpokenLanguageDTO
import com.example.cinemates.domain.model.tv.SpokenLanguage


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun SpokenLanguageDTO.mapToSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(this.name)
}
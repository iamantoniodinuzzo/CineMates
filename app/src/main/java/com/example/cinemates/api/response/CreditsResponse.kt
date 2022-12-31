package com.example.cinemates.api.response

import com.example.cinemates.model.Cast
import com.example.cinemates.model.Crew

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022
 */
class CreditsResponse(
    var id: Int,
    var cast: List<Cast>,
    var crew: List<Crew>
)
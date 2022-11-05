package com.example.cinemates.network.response

import com.example.cinemates.model.entities.Cast
import com.example.cinemates.model.entities.Crew

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022
 */
class CreditsResponse(
    var id: Int,
    var cast: List<Cast>,
    var crew: List<Crew>
)
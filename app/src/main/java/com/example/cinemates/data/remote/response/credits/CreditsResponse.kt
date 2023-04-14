package com.example.cinemates.data.remote.response.credits

import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.CrewDTO

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022
 */
class CreditsResponse(
    var id: Int,
    var cast: List<CastDTO>,
    var crew: List<CrewDTO>
)
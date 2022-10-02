package com.example.cinemates.model.data

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022
 */
class CreditsResponse(
    var id: Int,
    var cast: List<Cast> = listOf(),
    var crew: List<Crew> = listOf()
)
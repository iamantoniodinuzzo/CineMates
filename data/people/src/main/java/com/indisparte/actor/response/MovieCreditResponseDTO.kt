package com.indisparte.actor.response


data class MovieCreditResponseDTO(
    val cast: List<PersonAsCastDTO>,
    val crew: List<PersonAsCrewDTO>,
    val id: Int
)
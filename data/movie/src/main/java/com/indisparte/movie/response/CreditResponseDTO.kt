package com.indisparte.movie.response


import com.google.gson.annotations.SerializedName

data class CreditResponseDTO(
    val cast: List<CastDTO>,
    val crew: List<CrewDTO>,
    val id: Int
)
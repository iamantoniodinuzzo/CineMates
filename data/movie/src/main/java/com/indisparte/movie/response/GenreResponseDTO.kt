package com.indisparte.movie.response


import com.google.gson.annotations.SerializedName

data class GenreResponseDTO(
    val genres: List<GenreDTO>
)
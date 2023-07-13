package com.indisparte.movie.response


import com.google.gson.annotations.SerializedName

data class ImagesResponseDTO(
    val backdrops: List<BackdropDTO>,
    val id: Int,
    val logos: List<LogoDTO>,
    val posters: List<PosterDTO>
)
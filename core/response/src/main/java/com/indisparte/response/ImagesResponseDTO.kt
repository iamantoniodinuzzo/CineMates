package com.indisparte.response


data class ImagesResponseDTO(
    val backdrops: List<BackdropDTO>,
    val id: Int,
    val logos: List<LogoDTO>,
    val posters: List<PosterDTO>,
    val profiles: List<PosterDTO>
)
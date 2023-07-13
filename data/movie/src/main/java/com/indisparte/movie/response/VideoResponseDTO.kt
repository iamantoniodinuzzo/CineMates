package com.indisparte.movie.response


import com.google.gson.annotations.SerializedName

data class VideoResponseDTO(
    val id: Int,
    val results: List<ResultDTO>
)
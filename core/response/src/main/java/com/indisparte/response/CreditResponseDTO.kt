package com.indisparte.response



data class CreditResponseDTO<T,W>(
    val cast: List<T>,
    val crew: List<W>,
    val id: Int
)
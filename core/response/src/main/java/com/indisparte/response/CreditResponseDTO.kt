package com.indisparte.response



/**
 * Data class representing a credit response DTO.
 *
 * @param T The type of the cast members.
 * @param W The type of the crew members.
 * @property cast The list of cast members.
 * @property crew The list of crew members.
 * @property id The ID associated with the credit response.
 */
data class CreditResponseDTO<T, W>(
    val cast: List<T>,
    val crew: List<W>,
    val id: Int
)

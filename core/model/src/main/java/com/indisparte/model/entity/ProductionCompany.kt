package com.indisparte.model.entity


data class ProductionCompany(
    val id: Int,
    private val logoPath: String?,
    val name: String,
) {
    companion object {
        private const val IMAGE_BASE_URL_W780 = "https://image.tmdb.org/t/p/w780"
        private const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"
    }

    val completeLogoPathW780: String?
        get() = if (logoPath.isNullOrEmpty()) null else "${IMAGE_BASE_URL_W780}$logoPath"

    val completeLogoPathW500: String?
        get() = if (logoPath.isNullOrEmpty()) null else "${IMAGE_BASE_URL_W500}$logoPath"

}
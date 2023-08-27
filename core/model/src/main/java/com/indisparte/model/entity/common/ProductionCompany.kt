package com.indisparte.model.entity.common

import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780


data class ProductionCompany(
    val id: Int,
    private val logoPath: String?,
    val name: String,
) {
    val completeLogoPathW780: String?
        get() = if (logoPath.isNullOrEmpty()) null else "${IMAGE_BASE_URL_W780}$logoPath"

    val completeLogoPathW500: String?
        get() = if (logoPath.isNullOrEmpty()) null else "${IMAGE_BASE_URL_W500}$logoPath"

}
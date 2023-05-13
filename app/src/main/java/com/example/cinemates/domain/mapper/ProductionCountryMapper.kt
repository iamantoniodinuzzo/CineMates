package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.common.ProductionCountryDTO
import com.example.cinemates.domain.model.common.ProductionCountry


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun ProductionCountryDTO.mapToProductionCountry(): ProductionCountry {
    return ProductionCountry(
        name = this.name
    )
}
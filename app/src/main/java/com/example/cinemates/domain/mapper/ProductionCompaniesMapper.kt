package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.common.ProductionCompanyDTO
import com.example.cinemates.domain.model.common.ProductionCompany


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun ProductionCompanyDTO.mapToProductionCompany(): ProductionCompany {
    return ProductionCompany(
        id = this.id,
        logoPath = this.logoPath?:"",
        name = this.name
    )
}
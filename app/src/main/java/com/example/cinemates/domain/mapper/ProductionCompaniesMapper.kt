package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.common.ProductionCompanyDTO
import com.example.cinemates.domain.model.ProductionCompany


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class ProductionCompaniesMapper : Mapper<ProductionCompanyDTO, ProductionCompany> {
    override fun map(input: ProductionCompanyDTO): ProductionCompany {
        return ProductionCompany(
            id = input.id,
            logoPath = input.logoPath,
            name = input.name
        )
    }

}*/

fun ProductionCompanyDTO.mapToProductionCompany():ProductionCompany{
    return ProductionCompany(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name
    )
}
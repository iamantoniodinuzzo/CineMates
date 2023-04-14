package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.ProductionCompanyDTO
import com.example.cinemates.domain.model.ProductionCompany


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ProductionCompaniesMapper : Mapper<ProductionCompanyDTO, ProductionCompany> {
    override fun map(input: ProductionCompanyDTO): ProductionCompany {
        return ProductionCompany(
            id = input.id,
            logoPath = input.logoPath,
            name = input.name
        )
    }

}
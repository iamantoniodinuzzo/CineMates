package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.ProductionCountryDTO
import com.example.cinemates.domain.model.ProductionCountry


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class ProductionCountryMapper : Mapper<ProductionCountryDTO, ProductionCountry> {
    override fun map(input: ProductionCountryDTO): ProductionCountry {
        return ProductionCountry(
            name = input.name
        )
    }

}
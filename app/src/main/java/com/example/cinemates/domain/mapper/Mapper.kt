package com.example.cinemates.domain.mapper

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface Mapper<I, O> {
    fun map(input: I): O
}
package com.indisparte.database.entity.mapper

/**
 * An interface for mapping between domain and entity objects.
 *
 * This interface provides methods for converting domain objects to entity objects
 * and vice versa. It's commonly used in data mapping within the context of a data repository
 * to facilitate the transformation between data layer entities and domain layer objects.
 *
 * @param Domain The domain object type.
 * @param Entity The entity object type.
 *
 * @author Antonio Di Nuzzo
 */
interface EntityMapper<Domain, Entity> {

    /**
     * Converts a domain object to an entity object.
     *
     * @param domain The domain object to be converted.
     * @return The corresponding entity object.
     */
    fun asEntity(domain: Domain): Entity

    /**
     * Converts an entity object to a domain object.
     *
     * @param entity The entity object to be converted.
     * @return The corresponding domain object.
     */
    fun asDomain(entity: Entity): Domain
}

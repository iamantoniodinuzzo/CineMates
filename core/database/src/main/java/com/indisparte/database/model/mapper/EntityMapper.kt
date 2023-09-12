package com.indisparte.database.model.mapper

/**
 * An interface for mapping between domain objects and entity objects.
 *
 * @param Domain The type of domain object.
 * @param Entity The type of entity object.
 * @author Antonio Di Nuzzo
 */
interface EntityMapper<Domain, Entity> {

    /**
     * Creates an entity object from a domain object.
     *
     * @param domain The domain object to map.
     * @return The mapped entity object.
     */
    fun asEntity(domain: Domain): Entity

    /**
     * Creates a domain object from an entity object.
     *
     * @param entity The entity object to map.
     * @return The mapped domain object.
     */
    fun asDomain(entity: Entity): Domain
}
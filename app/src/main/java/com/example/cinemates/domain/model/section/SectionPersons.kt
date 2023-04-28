package com.example.cinemates.domain.model.section

import com.example.cinemates.domain.model.credits.Person

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionPersons(title: String, actors: List<Person>) : Section<Person>(title, actors)

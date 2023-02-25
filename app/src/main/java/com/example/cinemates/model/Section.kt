package com.example.cinemates.model

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Section<T>(val title:String, var items: List<T>){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Section<*>) return false

        if (title != other.title) return false
        if (items != other.items) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + items.hashCode()
        return result
    }
}

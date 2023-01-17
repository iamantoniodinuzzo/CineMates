package com.example.cinemates.model

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
class Video(
    var id: String,
    var key: String,
    var type: String,
    var name: String,
    var site: String,
    var published_at: String,
    var isOfficial: Boolean
) {



    override fun toString(): String {
        return "Video{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", published_at='" + published_at + '\'' +
                ", official=" + isOfficial +
                '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Video) return false

        if (id != other.id) return false
        if (key != other.key) return false
        if (type != other.type) return false
        if (name != other.name) return false
        if (site != other.site) return false
        if (published_at != other.published_at) return false
        if (isOfficial != other.isOfficial) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + site.hashCode()
        result = 31 * result + published_at.hashCode()
        result = 31 * result + isOfficial.hashCode()
        return result
    }
}
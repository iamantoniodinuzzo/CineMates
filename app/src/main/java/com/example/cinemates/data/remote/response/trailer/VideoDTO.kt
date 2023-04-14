package com.example.cinemates.data.remote.response.trailer

import com.google.gson.annotations.SerializedName

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
class VideoDTO(
    var id: String,
    var key: String,
    var type: String,
    var name: String,
    var site: String,
    @SerializedName("published_at")
    var publishedAt: String,
    var isOfficial: Boolean
) {



    override fun toString(): String {
        return "VideoDTO{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", published_at='" + publishedAt + '\'' +
                ", official=" + isOfficial +
                '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VideoDTO) return false

        if (id != other.id) return false
        if (key != other.key) return false
        if (type != other.type) return false
        if (name != other.name) return false
        if (site != other.site) return false
        if (publishedAt != other.publishedAt) return false
        if (isOfficial != other.isOfficial) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + site.hashCode()
        result = 31 * result + publishedAt.hashCode()
        result = 31 * result + isOfficial.hashCode()
        return result
    }
}
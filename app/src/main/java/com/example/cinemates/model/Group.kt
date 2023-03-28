package com.example.cinemates.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    @SerialName("episodes")
    val episodes: List<Episode>,
    @SerialName("id")
    val id: String,
    @SerialName("locked")
    val locked: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int
){
    override fun toString(): String {
        return "Group(name='$name',episodes=$episodes, id='$id', locked=$locked,  order=$order)"
    }
}
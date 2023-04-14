package com.example.cinemates.data.remote.response.tvShow


import kotlinx.serialization.SerialName

data class GroupDTO(
    @SerialName("episodeDTOS")
    val episodeDTOS: List<EpisodeDTO>,
    @SerialName("id")
    val id: String,
    @SerialName("locked")
    val locked: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int
) {
    override fun toString(): String {
        return "GroupDTO(name='$name',id='$id', locked=$locked,  order=$order, episodeDTOS=$episodeDTOS)"
    }
}
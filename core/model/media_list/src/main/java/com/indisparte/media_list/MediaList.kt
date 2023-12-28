package com.indisparte.media_list

data class MediaList(
    val id: Int = 0,
    val title: String,
    val description: String?,
){
    var updateDate: String = System.currentTimeMillis().toString()


}
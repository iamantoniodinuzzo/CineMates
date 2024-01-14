package com.indisparte.list.mapper

import com.indisparte.database.entity.ListEntity
import com.indisparte.media_list.MediaList

/**
 *@author Antonio Di Nuzzo
 */

fun MediaList.asEntity(): ListEntity {
    return ListEntity(
        listId = this.id,
        title = this.title,
        description = this.description,
        updateDate = this.updateDate
    )
}

fun ListEntity.asDomain(): MediaList {
    return MediaList(
        id = this.listId,
        title = this.title,
        description = this.description,
    )
}
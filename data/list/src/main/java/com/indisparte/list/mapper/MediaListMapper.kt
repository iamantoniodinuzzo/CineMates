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
        updateDate = this.updateDate,
        creationDate = this.creationDate,
        isPrivate = this.isPrivate,
        ownerId = this.ownerId,
    )
}

fun ListEntity.asDomain(): MediaList {
    return MediaList(
        id = this.listId,
        title = this.title,
        description = this.description, isPrivate = this.isPrivate, ownerId = this.ownerId,
        creationDate = this.creationDate, updateDate = this.updateDate
    )
}
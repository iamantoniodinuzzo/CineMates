package com.indisparte.list.mapper

import com.indisparte.database.entity.ListEntity
import com.indisparte.list.MediaList

/**
 *@author Antonio Di Nuzzo
 */

fun MediaList.asEntity(): ListEntity {
    return ListEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        updateDate = this.updateDate
    )
}

fun ListEntity.asDomain(): MediaList {
    return MediaList(
        id = this.id,
        title = this.title,
        description = this.description,
        updateDate = this.updateDate
    )
}
package com.indisparte.list.mapper

import com.indisparte.database.entity.DefaultListEntity
import com.indisparte.database.entity.ListEntity
import com.indisparte.media_list.DefaultMediaList
import com.indisparte.media_list.DefaultTitle
import com.indisparte.media_list.MediaList

/**
 *@author Antonio Di Nuzzo
 */

fun DefaultMediaList.asEntity(): DefaultListEntity {
    return DefaultListEntity(
        listId = this.id,
        defaultTitle = this.defaultTitle.defaultTitle,
        ownerId = this.ownerId,
    )
}

fun DefaultListEntity.asDomain(): DefaultMediaList {
    return DefaultMediaList(
        id = this.listId,
        defaultTitle = DefaultTitle.toDefaultTitle(this.defaultTitle),
        ownerId = this.ownerId,
    )
}
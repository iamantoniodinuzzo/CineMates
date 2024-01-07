package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val subscriptionDate: Date,
)

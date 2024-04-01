package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(tableName = "user", indices = [Index("userId")])
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val userId: Int,
    val name: String,
    val subscriptionDate: Date,
)

package com.marchuk.test.core.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
)
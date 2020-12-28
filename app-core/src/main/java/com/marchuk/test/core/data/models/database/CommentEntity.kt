package com.marchuk.test.core.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val postId: Int,
    val email: String,
    val name: String,
    val body: String,
)
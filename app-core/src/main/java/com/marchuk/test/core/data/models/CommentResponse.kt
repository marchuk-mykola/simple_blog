package com.marchuk.test.core.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CommentResponse(
    @Json(name = "body")
    val body: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "postId")
    val postId: Int
)
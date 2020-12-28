package com.marchuk.test.core.data.models
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
internal data class PostResponse(
    @Json(name = "body")
    val body: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "userId")
    val userId: Int
)
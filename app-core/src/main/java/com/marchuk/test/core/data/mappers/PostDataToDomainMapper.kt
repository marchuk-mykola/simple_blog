package com.marchuk.test.core.data.mappers

import com.marchuk.test.core.data.models.PostResponse
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.utils.Mapper

internal class PostDataToDomainMapper : Mapper<PostResponse, Post> {

    override fun invoke(input: PostResponse): Post {
        return Post(
            body = input.body,
            id = input.id,
            title = input.title,
            userId = input.userId
        )
    }

}
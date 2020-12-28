package com.marchuk.test.core.data.mappers

import com.marchuk.test.core.data.models.database.PostEntity
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.utils.Mapper

internal class PostEntityToDomainMapper : Mapper<PostEntity, Post> {

    override fun invoke(input: PostEntity): Post {
        return Post(
            body = input.body,
            id = input.id,
            title = input.title,
            userId = input.userId
        )
    }

}
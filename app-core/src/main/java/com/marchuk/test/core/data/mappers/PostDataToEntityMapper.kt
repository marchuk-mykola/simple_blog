package com.marchuk.test.core.data.mappers

import com.marchuk.test.core.data.models.PostResponse
import com.marchuk.test.core.data.models.database.PostEntity
import com.marchuk.test.utils.Mapper

internal class PostDataToEntityMapper : Mapper<PostResponse, PostEntity> {

    override fun invoke(input: PostResponse): PostEntity {
        return PostEntity(
            body = input.body,
            id = input.id,
            title = input.title,
            userId = input.userId
        )
    }

}
package com.marchuk.test.core.data.mappers

import com.marchuk.test.core.data.models.CommentResponse
import com.marchuk.test.core.data.models.database.CommentEntity
import com.marchuk.test.utils.Mapper

internal class CommentDataToEntityMapper : Mapper<CommentResponse, CommentEntity> {

    override fun invoke(input: CommentResponse): CommentEntity {
        return CommentEntity(
            body = input.body,
            email = input.email,
            id = input.id,
            name = input.name,
            postId = input.postId
        )
    }

}
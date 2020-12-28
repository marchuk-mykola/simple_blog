package com.marchuk.test.core.data.mappers

import com.marchuk.test.core.data.models.database.CommentEntity
import com.marchuk.test.core.domain.models.Comment
import com.marchuk.test.utils.Mapper

internal class CommentEntityToDomainMapper : Mapper<CommentEntity, Comment> {

    override fun invoke(input: CommentEntity): Comment {
        return Comment(
            body = input.body,
            email = input.email,
            id = input.id,
            name = input.name,
            postId = input.postId
        )
    }

}
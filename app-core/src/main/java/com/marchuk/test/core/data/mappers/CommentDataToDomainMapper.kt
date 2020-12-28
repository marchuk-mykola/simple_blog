package com.marchuk.test.core.data.mappers

import com.marchuk.test.core.data.models.CommentResponse
import com.marchuk.test.core.domain.models.Comment
import com.marchuk.test.utils.Mapper

internal class CommentDataToDomainMapper : Mapper<CommentResponse, Comment> {

    override fun invoke(input: CommentResponse): Comment {
        return Comment(
            body = input.body,
            email = input.email,
            id = input.id,
            name = input.name,
            postId = input.postId
        )
    }

}
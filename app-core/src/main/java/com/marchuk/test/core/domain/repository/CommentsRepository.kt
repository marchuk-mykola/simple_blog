package com.marchuk.test.core.domain.repository

import com.marchuk.test.core.domain.models.Comment
import io.reactivex.Single

interface CommentsRepository {
    fun getComments(postId: Int): Single<List<Comment>>
}
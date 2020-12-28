package com.marchuk.test.core.domain.useCases

import com.marchuk.test.core.domain.models.Comment
import com.marchuk.test.core.domain.repository.CommentsRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(private val commentsRepository: CommentsRepository) {

    operator fun invoke(postId: Int): Single<List<Comment>> {
        return commentsRepository.getComments(postId)
    }

}
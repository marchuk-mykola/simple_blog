package com.marchuk.test.core.domain.useCases

import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.domain.repository.PostsRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {

    operator fun invoke(): Single<List<Post>> {
        return postsRepository.getPosts()
    }

}
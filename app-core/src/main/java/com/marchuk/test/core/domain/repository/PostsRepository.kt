package com.marchuk.test.core.domain.repository

import com.marchuk.test.core.domain.models.Post
import io.reactivex.Single

interface PostsRepository {
    fun getPosts(): Single<List<Post>>
}
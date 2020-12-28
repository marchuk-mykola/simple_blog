package com.marchuk.test.posts.presentation

import com.marchuk.test.core.domain.models.Post

interface PostNavigator {
    fun navigateToPostDetails(post: Post)
}
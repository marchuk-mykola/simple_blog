package com.marchuk.test.blog

import com.marchuk.test.core.presentation.mvvm.BaseNavigator
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.post.details.presentation.ui.PostDetailsFragment
import com.marchuk.test.posts.presentation.PostNavigator
import javax.inject.Inject

class AppNavigator @Inject constructor() :
    BaseNavigator(),
    PostNavigator {

    override fun navigateToPostDetails(post: Post) {
        navController!!.navigate(
            R.id.open_post_details,
            PostDetailsFragment.createBundle(post)
        )
    }

}
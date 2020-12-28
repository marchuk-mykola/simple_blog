package com.marchuk.test.posts

import com.marchuk.test.posts.presentation.ui.PostsFragment
import dagger.Subcomponent

@Subcomponent
interface PostsComponent {

    fun inject(fragment: PostsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun build(): PostsComponent
    }
}

interface PostsComponentProvider {
    fun providePostComponent(): PostsComponent
}
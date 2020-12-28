package com.marchuk.test.post.details.di

import com.marchuk.test.post.details.presentation.ui.PostDetailsFragment
import dagger.Subcomponent

@Subcomponent
interface PostDetailsComponent {

    fun inject(fragment: PostDetailsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun build(): PostDetailsComponent
    }

}

interface PostDetailsComponentProvider {
    fun providePostDetailsComponent(): PostDetailsComponent
}
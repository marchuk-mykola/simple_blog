package com.marchuk.test.blog.di

import android.app.Activity
import com.marchuk.test.blog.BlogApplication
import com.marchuk.test.blog.MainActivity
import dagger.Subcomponent

fun Activity.daggerActivityComponent(): ActivityComponent {
    val activityComponent = BlogApplication.appComponent.activityComponent()
    return activityComponent.build()
}

@Subcomponent
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ActivityComponent
    }

}
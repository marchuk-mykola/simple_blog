package com.marchuk.test.blog

import android.app.Application
import com.marchuk.test.blog.di.AppComponent
import com.marchuk.test.blog.di.ApplicationModule
import com.marchuk.test.blog.di.DaggerAppComponent
import com.marchuk.test.core.data.di.DatabaseModule
import com.marchuk.test.core.data.di.MappersModule
import com.marchuk.test.core.data.di.NetworkModule
import com.marchuk.test.core.data.di.RepositoryModule
import com.marchuk.test.post.details.di.PostDetailsComponent
import com.marchuk.test.post.details.di.PostDetailsComponentProvider
import com.marchuk.test.posts.PostsComponent
import com.marchuk.test.posts.PostsComponentProvider
import timber.log.Timber

class BlogApplication : Application(), PostsComponentProvider, PostDetailsComponentProvider {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .mappersModule(MappersModule())
            .databaseModule(DatabaseModule(this))
            .build()
        appComponent.inject(this)
    }

    override fun providePostComponent(): PostsComponent {
        return appComponent.postsComponent().build()
    }

    override fun providePostDetailsComponent(): PostDetailsComponent {
        return appComponent.postDetailsComponent().build()
    }

}
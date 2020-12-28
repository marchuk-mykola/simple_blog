package com.marchuk.test.blog.di

import android.content.Context
import com.marchuk.test.blog.AppNavigator
import com.marchuk.test.blog.BlogApplication
import com.marchuk.test.core.data.di.DatabaseModule
import com.marchuk.test.core.data.di.MappersModule
import com.marchuk.test.core.data.di.NetworkModule
import com.marchuk.test.core.data.di.RepositoryModule
import com.marchuk.test.core.domain.di.UseCaseModule
import com.marchuk.test.post.details.di.PostDetailsComponent
import com.marchuk.test.posts.presentation.PostNavigator
import com.marchuk.test.posts.PostsComponent
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        MappersModule::class,
        DatabaseModule::class,
        NavigationModule::class,
        UseCaseModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(application: BlogApplication)
    fun activityComponent(): ActivityComponent.Builder
    fun postDetailsComponent(): PostDetailsComponent.Factory
    fun postsComponent(): PostsComponent.Factory
}

@Module
class ApplicationModule(
    private val app: BlogApplication,
) {

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext


    @Provides
    @Singleton
    fun provideNav(): AppNavigator = AppNavigator()

}

@Module
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun appNavigator(appNavigator: AppNavigator): PostNavigator

}
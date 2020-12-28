package com.marchuk.test.core.data.di

import android.app.Application
import com.marchuk.test.core.data.database.AppDatabase
import com.marchuk.test.core.data.database.dao.CommentDao
import com.marchuk.test.core.data.database.dao.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val app: Application) {

    @Provides
    @Singleton
    internal fun database(): AppDatabase = AppDatabase.buildDatabase(app.applicationContext)

    @Provides
    @Singleton
    internal fun commentDao(database: AppDatabase): CommentDao = database.commentDao()

    @Provides
    @Singleton
    internal fun postDao(database: AppDatabase): PostDao = database.postDao()

}


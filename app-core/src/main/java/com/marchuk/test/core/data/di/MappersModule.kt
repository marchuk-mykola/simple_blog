package com.marchuk.test.core.data.di

import com.marchuk.test.core.data.mappers.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MappersModule {

    @Provides
    @Singleton
    internal fun providePostToDomainMapper(): PostDataToDomainMapper = PostDataToDomainMapper()

    @Provides
    @Singleton
    internal fun providePostDataToEntityMapper(): PostDataToEntityMapper = PostDataToEntityMapper()

    @Provides
    @Singleton
    internal fun providePostEntityToDomainMapper(): PostEntityToDomainMapper = PostEntityToDomainMapper()

    @Provides
    @Singleton
    internal fun provideCommentDataToDomainMapper(): CommentDataToDomainMapper = CommentDataToDomainMapper()

    @Provides
    @Singleton
    internal fun provideCommentDataToEntityMapper(): CommentDataToEntityMapper = CommentDataToEntityMapper()

    @Provides
    @Singleton
    internal fun provideCommentEntityToDomainMapper(): CommentEntityToDomainMapper = CommentEntityToDomainMapper()

}
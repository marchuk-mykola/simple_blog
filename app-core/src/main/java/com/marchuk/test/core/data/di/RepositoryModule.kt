package com.marchuk.test.core.data.di

import com.marchuk.test.core.data.api.PostsApi
import com.marchuk.test.core.data.database.dao.CommentDao
import com.marchuk.test.core.data.database.dao.PostDao
import com.marchuk.test.core.data.mappers.*
import com.marchuk.test.core.data.repository.CommentsRepositoryImpl
import com.marchuk.test.core.data.repository.PostsRepositoryImpl
import com.marchuk.test.core.domain.repository.CommentsRepository
import com.marchuk.test.core.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideCommentsRepository(
        api: PostsApi,
        dao: CommentDao,
        commentDataToDomainMapper: CommentDataToDomainMapper,
        commentDataToEntityMapper: CommentDataToEntityMapper,
        commentEntityToDomainMapper: CommentEntityToDomainMapper,
    ): CommentsRepository {
        return CommentsRepositoryImpl(
            api,
            dao,
            commentDataToDomainMapper,
            commentDataToEntityMapper,
            commentEntityToDomainMapper
        )
    }

    @Provides
    @Singleton
    internal fun providePostsRepository(
        api: PostsApi,
        dao: PostDao,
        postDataToDomainMapper: PostDataToDomainMapper,
        postDataToEntityMapper: PostDataToEntityMapper,
        postEntityToDomainMapper: PostEntityToDomainMapper,
    ): PostsRepository {
        return PostsRepositoryImpl(api, dao, postDataToDomainMapper, postDataToEntityMapper, postEntityToDomainMapper)
    }


}
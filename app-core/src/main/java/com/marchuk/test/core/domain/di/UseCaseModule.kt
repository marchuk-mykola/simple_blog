package com.marchuk.test.core.domain.di

import com.marchuk.test.core.domain.repository.CommentsRepository
import com.marchuk.test.core.domain.repository.PostsRepository
import com.marchuk.test.core.domain.useCases.GetCommentsUseCase
import com.marchuk.test.core.domain.useCases.GetPostsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    internal fun getPostsUseCase(repository: PostsRepository) = GetPostsUseCase(repository)

    @Provides
    internal fun getCommentsUseCase(repository: CommentsRepository) = GetCommentsUseCase(repository)

}
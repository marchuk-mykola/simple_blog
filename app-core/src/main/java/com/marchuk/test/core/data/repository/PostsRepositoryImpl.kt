package com.marchuk.test.core.data.repository

import com.marchuk.test.core.data.api.PostsApi
import com.marchuk.test.core.data.database.dao.PostDao
import com.marchuk.test.core.data.mappers.PostDataToDomainMapper
import com.marchuk.test.core.data.mappers.PostDataToEntityMapper
import com.marchuk.test.core.data.mappers.PostEntityToDomainMapper
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.domain.repository.PostsRepository
import io.reactivex.Single
import timber.log.Timber

internal class PostsRepositoryImpl(
    private val api: PostsApi,
    private val dao: PostDao,
    private val postDataToDomainMapper: PostDataToDomainMapper,
    private val postDataToEntityMapper: PostDataToEntityMapper,
    private val postEntityToDomainMapper: PostEntityToDomainMapper,
) : PostsRepository {

    companion object {
        private const val POSTS_COUNT = 20
    }

    override fun getPosts(): Single<List<Post>> {
        return Single
            .concat(
                getFromDb(),
                getFromApi()
            )
            .filter { list -> list.isNotEmpty() }
            .firstOrError()
    }

    private fun getFromDb(): Single<List<Post>> {
        return dao
            .getPosts()
            .map { list ->
                list.map { postEntityToDomainMapper(it) }
            }
    }

    private fun getFromApi(): Single<List<Post>> {
        return api
            .getPosts()
            .map { list -> list.take(POSTS_COUNT) } // ograniczenie z zadania
            .doOnSuccess { list -> dao.insertPosts(list.map { postDataToEntityMapper(it) }) }
            .map { list -> list.map { postDataToDomainMapper(it) } }
    }

}
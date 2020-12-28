package com.marchuk.test.core.data.repository

import android.annotation.SuppressLint
import com.marchuk.test.core.data.api.PostsApi
import com.marchuk.test.core.data.database.dao.CommentDao
import com.marchuk.test.core.data.mappers.CommentDataToDomainMapper
import com.marchuk.test.core.data.mappers.CommentDataToEntityMapper
import com.marchuk.test.core.data.mappers.CommentEntityToDomainMapper
import com.marchuk.test.core.domain.models.Comment
import com.marchuk.test.core.domain.repository.CommentsRepository
import io.reactivex.Flowable
import io.reactivex.MaybeSource
import io.reactivex.Single

internal class CommentsRepositoryImpl(
    private val api: PostsApi,
    private val dao: CommentDao,
    private val commentDataToDomainMapper: CommentDataToDomainMapper,
    private val commentDataToEntityMapper: CommentDataToEntityMapper,
    private val commentEntityToDomainMapper: CommentEntityToDomainMapper,
) : CommentsRepository {

    companion object {
        private const val COMMENTS_COUNT = 3
    }

    override fun getComments(postId: Int): Single<List<Comment>> {
        return Single
            .concat(
                getFromDb(postId),
                getFromApi(postId)
            )
            .filter { list -> list.isNotEmpty() }
            .firstOrError()
    }

    private fun getFromDb(postId: Int): Single<List<Comment>> {
        return dao
            .getComments(postId)
            .map { list ->
                list.map { commentEntityToDomainMapper(it) }
            }
    }

    @SuppressLint("CheckResult")
    private fun getFromApi(postId: Int): Single<List<Comment>> {
        return api
            .getComments(postId)
            .map { list -> list.take(COMMENTS_COUNT) } // ograniczenie z zadania
            .doOnSuccess { list -> dao.insertComments(list.map { commentDataToEntityMapper(it) }) }
            .map { list -> list.map { commentDataToDomainMapper(it) } }
    }

}
package com.marchuk.test.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marchuk.test.core.RxImmediateSchedulerRule
import com.marchuk.test.core.data.api.PostsApi
import com.marchuk.test.core.data.database.dao.CommentDao
import com.marchuk.test.core.data.mappers.*
import com.marchuk.test.core.data.models.CommentResponse
import com.marchuk.test.core.data.models.database.CommentEntity
import com.marchuk.test.core.domain.models.Comment
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class CommentsRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK(relaxed = true)
    private lateinit var api: PostsApi

    @MockK(relaxed = true)
    private lateinit var dao: CommentDao

    private val commentDataToDomainMapper = CommentDataToDomainMapper()
    private val commentDataToEntityMapper = CommentDataToEntityMapper()
    private val commentEntityToDomainMapper = CommentEntityToDomainMapper()

    private lateinit var repository: CommentsRepositoryImpl

    private val postId: Int = 0
    private val emptyEntityList: List<CommentEntity> = listOf()
    private val responseList: List<CommentResponse> = listOf(
        CommentResponse(
            body = "",
            email = "",
            id = 0,
            name = "",
            postId = 0)
    )
    private val databaseList: List<CommentEntity> = responseList.map { commentDataToEntityMapper(it) }
    private val entitiesList: List<Comment> = responseList.map { commentDataToDomainMapper(it) }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        repository = CommentsRepositoryImpl(
            api,
            dao,
            commentDataToDomainMapper,
            commentDataToEntityMapper,
            commentEntityToDomainMapper
        )
    }

    @Test
    fun `no data in database, fetch data from api`() {
        every { dao.getComments(postId) } returns Single.just(emptyEntityList)
        every { api.getComments(postId) } returns Single.just(responseList)

        val testObserver = repository.getComments(postId).test()

        testObserver.assertNoErrors()
        testObserver.assertValue { list ->
            list == entitiesList
        }
        verify { dao.insertComments(databaseList) }
    }

    @Test
    fun `get comments from database`() {
        every { dao.getComments(0) } returns Single.just(databaseList)

        val testObserver = repository.getComments(0).test()

        testObserver.assertNoErrors()
        testObserver.assertValue { list ->
            list == databaseList.map { commentEntityToDomainMapper(it) }
        }
    }

    @Test
    fun `get comments when empty database and empty api response`() {
        every { dao.getComments(postId) } returns Single.just(emptyEntityList)
        every { api.getComments(postId) } returns Single.just(listOf())

        val testObserver = repository.getComments(postId).test()

        testObserver.assertError(NoSuchElementException::class.java)
    }


    @Test
    fun `empty database, error from api`() {
        every { dao.getComments(postId) } returns Single.just(emptyEntityList)
        every { api.getComments(postId) } returns Single.error(Throwable())

        val testObserver = repository.getComments(postId).test()

        testObserver.assertError(Throwable::class.java)
    }


}
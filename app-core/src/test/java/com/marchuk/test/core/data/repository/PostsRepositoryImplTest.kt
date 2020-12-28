package com.marchuk.test.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marchuk.test.core.RxImmediateSchedulerRule
import com.marchuk.test.core.data.api.PostsApi
import com.marchuk.test.core.data.database.dao.PostDao
import com.marchuk.test.core.data.mappers.PostDataToDomainMapper
import com.marchuk.test.core.data.mappers.PostDataToEntityMapper
import com.marchuk.test.core.data.mappers.PostEntityToDomainMapper
import com.marchuk.test.core.data.models.PostResponse
import com.marchuk.test.core.data.models.database.PostEntity
import com.marchuk.test.core.domain.models.Post
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
class PostsRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK(relaxed = true)
    private lateinit var api: PostsApi

    @MockK(relaxed = true)
    private lateinit var dao: PostDao

    private val postDataToDomainMapper = PostDataToDomainMapper()
    private val postDataToEntityMapper = PostDataToEntityMapper()
    private val postEntityToDomainMapper = PostEntityToDomainMapper()

    private lateinit var repository: PostsRepositoryImpl

    private val emptyEntityList: List<PostEntity> = listOf()
    private val responseList: List<PostResponse> = listOf(PostResponse(body = "", id = 0, title = "", userId = 0))
    private val databaseList: List<PostEntity> = responseList.map { postDataToEntityMapper(it) }
    private val entitiesList: List<Post> = responseList.map { postDataToDomainMapper(it) }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        repository = PostsRepositoryImpl(
            api,
            dao,
            postDataToDomainMapper,
            postDataToEntityMapper,
            postEntityToDomainMapper
        )
    }

    @Test
    fun `no data in database, fetch data from api`() {
        every { dao.getPosts() } returns Single.just(emptyEntityList)
        every { api.getPosts() } returns Single.just(responseList)

        val testObserver = repository.getPosts().test()

        testObserver.assertNoErrors()
        testObserver.assertValue { list ->
            list == entitiesList
        }
        verify { dao.insertPosts(databaseList) }
    }

    @Test
    fun `get posts from database`() {
        every { dao.getPosts() } returns Single.just(databaseList)

        val testObserver = repository.getPosts().test()

        testObserver.assertNoErrors()
        testObserver.assertValue { list ->
            list == databaseList.map { postEntityToDomainMapper(it) }
        }
    }

    @Test
    fun `get posts when empty database and empty api response`() {
        every { dao.getPosts() } returns Single.just(emptyEntityList)
        every { api.getPosts() } returns Single.just(listOf())

        val testObserver = repository.getPosts().test()

        testObserver.assertError(NoSuchElementException::class.java)
    }


    @Test
    fun `empty database, error from api`() {
        every { dao.getPosts() } returns Single.just(emptyEntityList)
        every { api.getPosts() } returns Single.error(Throwable())

        val testObserver = repository.getPosts().test()

        testObserver.assertError(Throwable::class.java)
    }


}
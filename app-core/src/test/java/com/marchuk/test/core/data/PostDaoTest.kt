package com.marchuk.test.core.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marchuk.test.core.data.database.AppDatabase
import com.marchuk.test.core.data.database.dao.PostDao
import com.marchuk.test.core.data.models.database.PostEntity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var postDao: PostDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries() //only for testing
            .build()

        postDao = database.postDao()
    }

    private val dummyPost1 = PostEntity(id = 0, body = "", title = "", userId = 0)
    private val dummyPost2 = PostEntity(id = 1, body = "", title = "", userId = 0)
    private val dummyPost3 = PostEntity(id = 2, body = "", title = "", userId = 0)

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insert and check inserted id's`() {
        postDao
            .insertPosts(listOf(dummyPost1, dummyPost2))
            .blockingAwait()

        // Test OnConflictStrategy
        postDao
            .insertPosts(listOf(dummyPost2, dummyPost3))
            .blockingAwait()

        postDao
            .getPosts()
            .test()
            .assertValue { list ->
                list.size == 3 && list.first().id == 0 && list.last().id == 2
            }
    }

}
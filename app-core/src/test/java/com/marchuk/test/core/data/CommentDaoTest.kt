package com.marchuk.test.core.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marchuk.test.core.data.database.AppDatabase
import com.marchuk.test.core.data.database.dao.CommentDao
import com.marchuk.test.core.data.models.database.CommentEntity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var commentDao: CommentDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries() //only for testing
            .build()

        commentDao = database.commentDao()
    }

    private val dummyComment1 = CommentEntity(body = "", email = "", id = 0, name = "", postId = 0)
    private val dummyComment2 = CommentEntity(body = "", email = "", id = 1, name = "", postId = 0)
    private val dummyComment3 = CommentEntity(body = "", email = "", id = 2, name = "", postId = 0)

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insert and check inserted id's`() {
        commentDao
            .insertComments(listOf(dummyComment1, dummyComment2))
            .blockingAwait()

        // Test OnConflictStrategy
        commentDao
            .insertComments(listOf(dummyComment2, dummyComment3))
            .blockingAwait()

        commentDao
            .getComments(0)
            .test()
            .assertValue { list ->
                list.size == 3 && list.first().id == 0 && list.last().id == 2
            }
    }

}
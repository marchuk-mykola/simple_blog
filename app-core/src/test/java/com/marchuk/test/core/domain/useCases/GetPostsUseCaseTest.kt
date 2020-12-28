package com.marchuk.test.core.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marchuk.test.core.RxImmediateSchedulerRule
import com.marchuk.test.core.domain.repository.PostsRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetPostsUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK(relaxed = true)
    private lateinit var repository: PostsRepository
    private lateinit var getPostsUseCase: GetPostsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        getPostsUseCase = GetPostsUseCase(repository)
    }

    @Test
    operator fun invoke() {
        val testObserver = getPostsUseCase.invoke().test()
        testObserver.assertNoErrors()
        verify { repository.getPosts() }
    }

}
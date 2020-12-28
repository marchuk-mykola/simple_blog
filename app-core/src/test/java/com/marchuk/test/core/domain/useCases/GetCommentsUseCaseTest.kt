package com.marchuk.test.core.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marchuk.test.core.RxImmediateSchedulerRule
import com.marchuk.test.core.domain.repository.CommentsRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCommentsUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK(relaxed = true)
    private lateinit var repository: CommentsRepository
    private lateinit var getCommentsUseCase: GetCommentsUseCase
    private val postId: Int = 0

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        getCommentsUseCase = GetCommentsUseCase(repository)
    }

    @Test
    fun invoke() {
        val testObserver = getCommentsUseCase.invoke(postId).test()
        testObserver.assertNoErrors()
        verify { repository.getComments(postId) }
    }

}
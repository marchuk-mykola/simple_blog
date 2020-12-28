package com.marchuk.test.post.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.marchuk.test.core.RxImmediateSchedulerRule
import com.marchuk.test.core.domain.models.Comment
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.domain.useCases.GetCommentsUseCase
import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import com.marchuk.test.core.presentation.delegates.RecyclerDelegate
import com.marchuk.test.post.details.presentation.models.CommentsHolder
import com.marchuk.test.post.details.presentation.viewmodel.PostDetailsViewModel
import io.mockk.*
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDetailsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private val getCommentsUseCase: GetCommentsUseCase = mockk(relaxed = true)

    private lateinit var viewModel: PostDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        viewModel = PostDetailsViewModel(getCommentsUseCase)
    }

    private val dummyComment = Comment(body = "", email = "", id = 0, name = "", postId = 1)
    private val dummyList = listOf(dummyComment)
    private val dummyPost = Post(body = "", id = 1, title = "", userId = 0, isExpanded = false)

    private fun createObserver(): Observer<List<DelegateAdapterItem>> = spyk(Observer { })

    @Test
    fun `success when get comments`() {
        // Given
        val mockedObserver = createObserver()
        val viewStateSlots = mutableListOf<List<DelegateAdapterItem>>()
        viewModel.state.observeForever(mockedObserver)

        // when
        every { getCommentsUseCase.invoke(any()) } returns Single.just(dummyList)
        viewModel.getComments(dummyPost)

        // Then
        verify { mockedObserver.onChanged(capture(viewStateSlots)) }
        verify { getCommentsUseCase.invoke(any()) }
        // onSubscribe
        Truth.assertThat(viewStateSlots.first()).containsExactly(dummyPost, RecyclerDelegate.Loading)
        // onSuccess
        Truth.assertThat(viewStateSlots.last()).containsExactly(dummyPost, CommentsHolder(dummyList))
        Truth.assertThat(viewStateSlots.size).isEqualTo(2)
        viewModel.state.removeObserver(mockedObserver)
    }

    @Test
    fun `error when get comments`() {
        // Given
        val mockedObserver = createObserver()
        val viewStateSlots = mutableListOf<List<DelegateAdapterItem>>()
        viewModel.state.observeForever(mockedObserver)

        // when
        every { getCommentsUseCase.invoke(any()) } returns Single.error(Throwable())
        viewModel.getComments(dummyPost)

        // Then
        verify { mockedObserver.onChanged(capture(viewStateSlots)) }
        verify { getCommentsUseCase.invoke(any()) }
        // onSubscribe
        Truth.assertThat(viewStateSlots.first()).containsExactly(dummyPost, RecyclerDelegate.Loading)
        // onError
        Truth.assertThat(viewStateSlots.last()).containsExactly(dummyPost, RecyclerDelegate.Error)
        Truth.assertThat(viewStateSlots.size).isEqualTo(2)
        viewModel.state.removeObserver(mockedObserver)
    }


}
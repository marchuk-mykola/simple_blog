package com.marchuk.test.posts.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.marchuk.test.core.RxImmediateSchedulerRule
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.domain.useCases.GetPostsUseCase
import com.marchuk.test.posts.presentation.PostNavigator
import com.marchuk.test.utils.Resource
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
class PostsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private val getPostsUseCase: GetPostsUseCase = mockk(relaxed = true)
    private val postNavigator: PostNavigator = mockk(relaxed = true)

    private lateinit var viewModel: PostsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        viewModel = PostsViewModel(getPostsUseCase, postNavigator)
    }

    private val dummyPost = Post(body = "", id = 0, title = "", userId = 0, isExpanded = false)

    private fun createObserver(): androidx.lifecycle.Observer<Resource<List<Post>>> =
        spyk(androidx.lifecycle.Observer { })

    @Test
    fun `success when get posts`() {
        // Given
        val mockedObserver = createObserver()
        val viewStateSlots = mutableListOf<Resource<List<Post>>>()
        val list = listOf(dummyPost)
        val resource = Resource.Success(list)
        viewModel.state.observeForever(mockedObserver)

        // when
        every { getPostsUseCase.invoke() } returns Single.just(list)
        viewModel.getPosts()

        // Then
        verify { mockedObserver.onChanged(capture(viewStateSlots)) }
        verify { getPostsUseCase.invoke() }
        Truth.assertThat(viewStateSlots.first()).isEqualTo(Resource.Loading)
        Truth.assertThat(viewStateSlots.last()).isEqualTo(resource)
        Truth.assertThat(viewStateSlots.size).isEqualTo(2)
        viewModel.state.removeObserver(mockedObserver)
    }

    @Test
    fun `navigation invoked`() {
        viewModel.navigateToDetails(dummyPost)
        verify { postNavigator.navigateToPostDetails(dummyPost) }
    }

    @Test
    fun `error when get posts`() {
        // Given
        val mockedObserver = createObserver()
        viewModel.state.observeForever(mockedObserver)

        // when
        every { getPostsUseCase.invoke() } returns Single.error(Throwable())
        viewModel.getPosts()

        val viewStateSlots = mutableListOf<Resource<List<Post>>>()
        verify { mockedObserver.onChanged(capture(viewStateSlots)) }
        verify { getPostsUseCase.invoke() }

        // Then
        Truth.assertThat(viewStateSlots.first()).isEqualTo(Resource.Loading)
        Truth.assertThat(viewStateSlots.last()).isInstanceOf(Resource.Error::class.java)
        Truth.assertThat(viewStateSlots.size).isEqualTo(2)
    }


}


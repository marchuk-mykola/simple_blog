package com.marchuk.test.posts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marchuk.test.utils.Resource
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.domain.useCases.GetPostsUseCase
import com.marchuk.test.core.presentation.mvvm.BaseViewModel
import com.marchuk.test.core.presentation.extensions.setError
import com.marchuk.test.core.presentation.extensions.setLoading
import com.marchuk.test.core.presentation.extensions.setSuccess
import com.marchuk.test.posts.presentation.PostNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val postNavigator: PostNavigator
) : BaseViewModel() {

    private val _state = MutableLiveData<Resource<List<Post>>>()
    val state: LiveData<Resource<List<Post>>> = _state

    init {
        getPosts()
    }

    fun navigateToDetails(post: Post) {
        postNavigator.navigateToPostDetails(post)
    }

    fun getPosts() {
        addDisposable {
            getPostsUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _state.setLoading()
                }
                .subscribe(
                    {
                        _state.setSuccess(it)
                    }, {
                        _state.setError(it.localizedMessage ?: String())
                    }
                )
        }
    }

}









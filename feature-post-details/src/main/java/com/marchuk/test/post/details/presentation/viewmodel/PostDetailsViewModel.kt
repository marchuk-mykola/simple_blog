package com.marchuk.test.post.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.domain.useCases.GetCommentsUseCase
import com.marchuk.test.core.presentation.mvvm.BaseViewModel
import com.marchuk.test.core.presentation.delegates.RecyclerDelegate
import com.marchuk.test.post.details.presentation.models.CommentsHolder
import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
) : BaseViewModel() {

    /**
     * This is recycler-based state, so we don't need use Resource<T>
     */
    private val _state = MutableLiveData<List<DelegateAdapterItem>>()
    val state: LiveData<List<DelegateAdapterItem>> = _state

    fun getComments(post: Post) {
        addDisposable {
            getCommentsUseCase(post.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _state.postValue(listOf(post, RecyclerDelegate.Loading))
                }
                .subscribe(
                    {
                        _state.postValue(listOf(post, CommentsHolder(it)))
                    }, {
                        _state.postValue(listOf(post, RecyclerDelegate.Error))
                    }
                )
        }
    }

}
package com.marchuk.test.core.presentation.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(func: () -> Disposable) {
        compositeDisposable.add(func())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
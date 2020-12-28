package com.marchuk.test.core.presentation.extensions

import androidx.lifecycle.MutableLiveData
import com.marchuk.test.utils.Event
import com.marchuk.test.utils.Resource

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) {
    this.postValue(Resource.Success(data))
}

fun <T> MutableLiveData<Resource<T>>.setLoading() {
    this.postValue(Resource.Loading)
}

fun <T> MutableLiveData<Resource<T>>.setError(msg: String) {
    this.postValue(Resource.Error(Event(msg)))
}


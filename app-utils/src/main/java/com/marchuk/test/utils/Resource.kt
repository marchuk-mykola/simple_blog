package com.marchuk.test.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out T> {

    /**
     * Success response with body
     */
    data class Success<T>(val body: T) : Resource<T>()

    object Loading : Resource<Nothing>()

    data class Error(val msg: Event<String?>) : Resource<Nothing>()

}
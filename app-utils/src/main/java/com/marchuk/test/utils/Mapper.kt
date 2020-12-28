package com.marchuk.test.utils

fun interface Mapper<I, O> {
    operator fun invoke(input: I): O
}
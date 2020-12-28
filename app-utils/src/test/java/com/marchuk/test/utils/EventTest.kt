package com.marchuk.test.utils

import com.google.common.truth.Truth
import org.junit.Test

class EventTest {

    @Test
    fun eventTest() {
        val mockEvent = Event(true)

        Truth.assertThat(mockEvent.getContentIfNotHandledElseNull()).isTrue()
        Truth.assertThat(mockEvent.hasBeenHandled).isTrue()
        Truth.assertThat(mockEvent.peekContent()).isTrue()
        Truth.assertThat(mockEvent.getContentIfNotHandledElseNull()).isNull()
    }

}
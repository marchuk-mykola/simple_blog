package com.marchuk.test.core.data.mappers

import com.google.common.truth.Truth
import com.marchuk.test.core.data.models.PostResponse
import com.marchuk.test.core.data.models.database.PostEntity
import org.junit.Test

class PostDataToEntityMapperTest {

    private val mapper = PostDataToEntityMapper()

    @Test
    fun invoke() {
        val given = PostResponse(body = "", id = 10, title = "", userId = 0)
        val expected = PostEntity(body = "", id = 10, title = "", userId = 0)

        val mapped = mapper(given)
        Truth.assertThat(mapped).isEqualTo(expected)
    }

}
package com.marchuk.test.core.data.mappers

import com.google.common.truth.Truth
import com.marchuk.test.core.data.models.PostResponse
import com.marchuk.test.core.data.models.database.PostEntity
import com.marchuk.test.core.domain.models.Post
import org.junit.Test

import org.junit.Assert.*

class PostEntityToDomainMapperTest {

    private val mapper = PostEntityToDomainMapper()

    @Test
    fun invoke() {
        val given = PostEntity(body = "", id = 10, title = "", userId = 0)
        val expected = Post(body = "", id = 10, title = "", userId = 0)

        val mapped = mapper(given)
        Truth.assertThat(mapped).isEqualTo(expected)
    }

}
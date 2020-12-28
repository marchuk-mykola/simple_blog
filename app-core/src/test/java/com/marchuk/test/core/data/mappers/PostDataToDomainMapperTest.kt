package com.marchuk.test.core.data.mappers

import com.google.common.truth.Truth
import com.marchuk.test.core.data.models.CommentResponse
import com.marchuk.test.core.data.models.PostResponse
import com.marchuk.test.core.data.models.database.CommentEntity
import com.marchuk.test.core.domain.models.Post
import org.junit.Test

import org.junit.Assert.*

class PostDataToDomainMapperTest {

    private val mapper = PostDataToDomainMapper()

    @Test
    fun invoke() {
        val given = PostResponse(body = "", id = 10, title = "", userId = 0)
        val expected = Post(body = "", id = 10, title = "", userId = 0)

        val mapped = mapper(given)
        Truth.assertThat(mapped).isEqualTo(expected)
    }
}
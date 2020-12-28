package com.marchuk.test.core.data.mappers

import com.google.common.truth.Truth
import com.marchuk.test.core.data.models.CommentResponse
import com.marchuk.test.core.domain.models.Comment
import org.junit.Test

class CommentDataToDomainMapperTest {

    private val mapper = CommentDataToDomainMapper()

    @Test
    fun invoke() {
        val given = CommentResponse(body = "", email = "", id = 5, name = "", postId = 0)
        val expected = Comment(body = "", email = "", id = 5, name = "", postId = 0)

        val mapped = mapper(given)
        Truth.assertThat(mapped).isEqualTo(expected)
    }

}
package com.marchuk.test.core.data.api

import com.marchuk.test.core.data.models.CommentResponse
import com.marchuk.test.core.data.models.PostResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PostsApi {

    @GET("posts")
    fun getPosts(): Single<List<PostResponse>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int): Single<List<CommentResponse>>

}
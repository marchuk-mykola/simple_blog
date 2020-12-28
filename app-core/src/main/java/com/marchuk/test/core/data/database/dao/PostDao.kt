package com.marchuk.test.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchuk.test.core.data.models.database.PostEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface PostDao {

    @Query("SELECT * from postentity")
    fun getPosts(): Single<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPosts(posts: List<PostEntity>): Completable

}
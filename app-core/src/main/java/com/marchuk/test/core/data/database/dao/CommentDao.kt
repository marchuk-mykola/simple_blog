package com.marchuk.test.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchuk.test.core.data.models.database.CommentEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CommentDao {

    @Query("SELECT * from commententity WHERE postId = :postId")
    fun getComments(postId: Int): Single<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComments(comments: List<CommentEntity>): Completable

}
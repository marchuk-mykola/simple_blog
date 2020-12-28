package com.marchuk.test.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marchuk.test.core.data.database.dao.CommentDao
import com.marchuk.test.core.data.database.dao.PostDao
import com.marchuk.test.core.data.models.database.CommentEntity
import com.marchuk.test.core.data.models.database.PostEntity

@Database(entities = [PostEntity::class, CommentEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun commentDao(): CommentDao
    abstract fun postDao(): PostDao

    companion object {
        private const val DATABASE_NAME = "Application Database"

        fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

    }

}
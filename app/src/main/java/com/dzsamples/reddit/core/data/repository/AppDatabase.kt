package com.dzsamples.reddit.core.data.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dzsamples.reddit.core.model.CommentData
import com.dzsamples.reddit.core.model.NewsData

/**
 * Criado por danilodequeiroz on 21/10/17.
 */
@Database(entities = arrayOf(NewsData::class, CommentData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
    abstract fun getCommentsDao(): CommentsDao
}
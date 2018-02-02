package com.dzsamples.reddit.core.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dzsamples.reddit.core.model.NewsData
import io.reactivex.Maybe

/**
 * Criado por danilodequeiroz on 21/10/17.
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): Maybe<MutableList<NewsData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(newsData: NewsData)
}
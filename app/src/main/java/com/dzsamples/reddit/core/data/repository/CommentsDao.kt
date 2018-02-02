package com.dzsamples.reddit.core.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dzsamples.reddit.core.model.CommentData
import io.reactivex.Maybe

/**
 * Criado por danilodequeiroz on 21/10/17.
 */
@Dao
interface CommentsDao {
    @Query("SELECT * FROM comments WHERE id = :id")
    fun getCommentsForId(id: String): Maybe<List<CommentData>>

    @Insert
    fun insert(commentData: CommentData)
}
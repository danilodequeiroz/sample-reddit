package com.dzsamples.reddit.core.data.repository

import com.dzsamples.reddit.core.model.CommentData
import com.dzsamples.reddit.core.model.NewsData
import io.reactivex.Maybe

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
interface CachedRepository {
    fun getAllNews(): Maybe<MutableList<NewsData>>
    fun insertNews(newsData: NewsData)
    fun getCommentsForId(id: String): Maybe<List<CommentData>>
    fun insertComments(commentData: CommentData)
}
package com.dzsamples.reddit.core.data

import com.dzsamples.reddit.core.model.CommentData
import com.dzsamples.reddit.core.model.NewsData
import com.dzsamples.reddit.core.model.NewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
interface DataManagerInterface {
    fun getNewsReddits(): Maybe<NewsDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<NewsDataResponse>
    fun insertNews(newsData: NewsData)
    fun getAllNewsLocal(): Maybe<MutableList<NewsData>>

    fun getAllCommentsNew(id: String): Maybe<MutableList<CommentData>>
    fun getAllCommentsNewLocal(id: String): Maybe<List<CommentData>>
    fun insertComments(commentData: CommentData)
}

package com.dzsamples.reddit.core.data.api

import com.dzsamples.reddit.core.model.CommentsDataNvl2Response
import com.dzsamples.reddit.core.model.NewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
interface RemoteDataSource {
    fun getNewReddits(): Maybe<NewsDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<NewsDataResponse>
    fun getAllCommentsNew(id: String): Maybe<List<CommentsDataNvl2Response>>
}
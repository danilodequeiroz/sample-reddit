package com.dzsamples.reddit.core.data

import com.dzsamples.reddit.core.data.api.RemoteDataSource
import com.dzsamples.reddit.core.data.repository.CachedRepository
import com.dzsamples.reddit.core.model.CommentData
import com.dzsamples.reddit.core.model.NewsData
import com.dzsamples.reddit.core.model.NewsDataResponse
import io.reactivex.Maybe
import timber.log.Timber
import javax.inject.Inject

/**
 * Criado por danilodequeiroz on 18/10/17.
 */

class DataManager @Inject constructor(
        private val apiDataSource: RemoteDataSource,
        private val cachedRepository: CachedRepository) : DataManagerInterface {

    override fun getNextPageNewReddit(after: String): Maybe<NewsDataResponse> {
        return apiDataSource.getNextPageNewReddit(after)
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getNewsReddits(): Maybe<NewsDataResponse> {
        return apiDataSource.getNewReddits()
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getAllNewsLocal(): Maybe<MutableList<NewsData>> {
        return cachedRepository.getAllNews()
    }


    override fun insertNews(newsData: NewsData) {
        cachedRepository.insertNews(newsData)
    }

    override fun getAllCommentsNew(id: String): Maybe<MutableList<CommentData>> {
        return apiDataSource.getAllCommentsNew(id)
                .concatMap({ dataWrappers ->
                    val comments = mutableListOf<CommentData>()
                    val idNews = dataWrappers[0].data?.children?.first()?.data?.id

                    dataWrappers[1].data?.children?.forEach { redditCommentsChildrenDataNvl2Response ->
                        redditCommentsChildrenDataNvl2Response.data?.let {
                            it.id = idNews ?: ""
                            comments.add(it)
                            insertComments(it)
                        }
                    }

                    Maybe.just(comments)
                })
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getAllCommentsNewLocal(id: String): Maybe<List<CommentData>> {
        return cachedRepository.getCommentsForId(id)
    }

    override fun insertComments(commentData: CommentData) {
        cachedRepository.insertComments(commentData)
    }
}
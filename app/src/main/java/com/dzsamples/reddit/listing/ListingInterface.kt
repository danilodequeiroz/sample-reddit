package com.dzsamples.reddit.listing

import com.dzsamples.reddit.core.global.ErrorViewInterface
import com.dzsamples.reddit.core.global.ProgressViewInterface
import com.dzsamples.reddit.core.model.NewsData

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
interface ListingInterface {

    interface ListingView : ProgressViewInterface, ErrorViewInterface {
        fun loadNewReddits(news: List<NewsData>)
        fun saveNextPage(after: String)
    }

    interface ListingPresenter {
        fun onViewCreated(connectedToInternet: Boolean)
        fun onDestroy()
        fun loadNextPageNewRedditsList(after: String?)
    }
}
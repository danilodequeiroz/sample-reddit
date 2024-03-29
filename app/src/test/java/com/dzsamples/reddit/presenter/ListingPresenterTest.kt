package com.dzsamples.reddit.presenter

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.dzsamples.reddit.BuildConfig
import com.dzsamples.reddit.core.data.DataManagerInterface
import com.dzsamples.reddit.core.model.NewsData
import com.dzsamples.reddit.core.model.NewsDataResponse
import com.dzsamples.reddit.listing.ListingInterface
import com.dzsamples.reddit.listing.ListingPresenterImpl
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

/**
 * Criado por danilodequeiroz on 21/10/17.
 */
@RunWith(MockitoJUnitRunner::class)
@Config(constants = BuildConfig::class)
class ListingPresenterTest {
    @Mock private
    lateinit var listingView: ListingInterface.ListingView

    @Mock private
    lateinit var dataManager: DataManagerInterface

    @Mock private
    lateinit var redditNewsDataResponse: NewsDataResponse

    private var listingPresenter: ListingInterface.ListingPresenter? = null

    @Before
    fun setUp() {
        listingPresenter = getListingPresenter()
    }

    @Test
    fun loading_News_Posts_In_The_ListingView() {
        val hasInternet = true

        whenever(dataManager.getNewsReddits()).thenReturn(Maybe.just(redditNewsDataResponse))
        listingPresenter?.onViewCreated(hasInternet)

        verify(this.listingView).showProgressBar()
        verify(this.listingView).saveNextPage(Mockito.anyString())
        verify(this.listingView).loadNewReddits(ArgumentMatchers.anyList<NewsData>())
        verify(this.listingView).hideProgressBar()
    }

    @Test
    fun loading_Next_Page_In_The_ListingView() {
        whenever(dataManager.getNextPageNewReddit(Mockito.anyString())).thenReturn(Maybe.just(redditNewsDataResponse))
        listingPresenter?.loadNextPageNewRedditsList(Mockito.anyString())

        verify(this.listingView).saveNextPage(Mockito.anyString())
        verify(this.listingView).loadNewReddits(ArgumentMatchers.anyList<NewsData>())
    }

    private fun getListingPresenter(): ListingPresenterImpl {
        return ListingPresenterImpl(listingView, dataManager)
    }
}
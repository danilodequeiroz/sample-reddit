package com.dzsamples.reddit.core.di

import com.dzsamples.reddit.detail.DetailFragment
import com.dzsamples.reddit.listing.ListingFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Criado por danilodequeiroz on 29/11/17.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (ServiceModule::class), (RoomModule::class)])
interface RedditSampleComponent {
    fun inject(listingFragment: ListingFragment)
    fun inject(detailFragment: DetailFragment)
}
package com.dzsamples.reddit.core.di

import com.dzsamples.reddit.AppDelegate
import com.dzsamples.reddit.core.global.DataConstants.API_URL
import com.dzsamples.reddit.core.global.DataConstants.DATABASE_URL

/**
 * Criado por danilodequeiroz on 29/11/17.
 */
object RedditSampleInjector {
    lateinit var redditSampleComponent: RedditSampleComponent
        private set

    fun initialize(appDelegate: AppDelegate) {
        redditSampleComponent = DaggerRedditSampleComponent.builder()
                .networkModule(NetworkModule(API_URL))
                .serviceModule(ServiceModule())
                .roomModule(RoomModule(appDelegate, DATABASE_URL))
                .build()
    }
}
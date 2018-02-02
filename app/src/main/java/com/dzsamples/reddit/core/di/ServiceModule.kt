package com.dzsamples.reddit.core.di

import com.dzsamples.reddit.core.data.DataManager
import com.dzsamples.reddit.core.data.DataManagerInterface
import com.dzsamples.reddit.core.data.api.RedditService
import com.dzsamples.reddit.core.data.api.RemoteDataSource
import com.dzsamples.reddit.core.data.api.RestApiDataSource
import com.dzsamples.reddit.core.data.repository.CachedRepository
import com.dzsamples.reddit.core.data.repository.CommentsDao
import com.dzsamples.reddit.core.data.repository.NewsDao
import com.dzsamples.reddit.core.data.repository.RedditRepositoryDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Criado por danilodequeiroz on 29/11/17.
 */

@Module(includes = [(NetworkModule::class), (RoomModule::class)])
class ServiceModule {

    @Provides
    @Singleton
    fun providesApiDataSource(redditService: RedditService) : RemoteDataSource = RestApiDataSource(redditService)

    @Provides
    @Singleton
    fun providesRepositoryDataSource(newsDao: NewsDao, commentsDao: CommentsDao):
            CachedRepository = RedditRepositoryDataSource(newsDao, commentsDao)

    @Provides
    @Singleton
    fun providesDataManager(remoteDataSource: RemoteDataSource, redditRepositoryDataSource: RedditRepositoryDataSource):
            DataManagerInterface = DataManager(remoteDataSource, redditRepositoryDataSource)
}
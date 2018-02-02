package com.dzsamples.reddit.core.di

import android.arch.persistence.room.Room
import com.dzsamples.reddit.AppDelegate
import com.dzsamples.reddit.core.data.repository.AppDatabase
import com.dzsamples.reddit.core.data.repository.CommentsDao
import com.dzsamples.reddit.core.data.repository.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Criado por danilodequeiroz on 30/11/17.
 */
@Module
class RoomModule(delegate: AppDelegate, urlDatabase: String) {
    private var appDatabase: AppDatabase = Room.databaseBuilder(delegate, AppDatabase::class.java, urlDatabase).build()

    @Provides
    @Singleton
    fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    @Singleton
    fun providesNewsDao(): NewsDao {
        return appDatabase.getNewsDao()
    }

    @Provides
    @Singleton
    fun providesCommentsDao(): CommentsDao {
        return appDatabase.getCommentsDao()
    }

}
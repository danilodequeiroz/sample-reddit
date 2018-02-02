package com.dzsamples.reddit

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.dzsamples.reddit.core.di.RedditSampleInjector
import com.dzsamples.reddit.global.RedditSampleTimber
import io.fabric.sdk.android.Fabric
import okhttp3.HttpUrl
import timber.log.Timber


/**
 * Criado por danilodequeiroz on 18/10/17.
 */
class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            initStetho()
        }
        Timber.plant(RedditSampleTimber())
        Fabric.with(this, Crashlytics())
        initDagger()
    }

    private fun initDagger() {
        RedditSampleInjector.initialize(this)
    }

    companion object {
        var URL: HttpUrl? = null
    }


    private fun initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .build())
    }

}
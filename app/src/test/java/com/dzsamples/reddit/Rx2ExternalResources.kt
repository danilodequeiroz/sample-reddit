package com.dzsamples.reddit

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.ExternalResource

/**
 * Criado por danilodequeiroz on 21/10/17.
 */
class Rx2ExternalResources : ExternalResource() {
    override fun before() {
        val scheduler = Schedulers.trampoline()
        RxJavaPlugins.setIoSchedulerHandler({ _ -> scheduler })
        RxJavaPlugins.setInitComputationSchedulerHandler({ _ -> scheduler })
        RxJavaPlugins.setComputationSchedulerHandler({ _ -> scheduler })
        RxJavaPlugins.setNewThreadSchedulerHandler({ _ -> scheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler({ _ -> scheduler })
        RxAndroidPlugins.setMainThreadSchedulerHandler({ _ -> scheduler })
    }

    override fun after() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}
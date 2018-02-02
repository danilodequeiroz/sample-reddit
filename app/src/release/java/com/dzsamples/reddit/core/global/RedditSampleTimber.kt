package com.dzsamples.reddit.global

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

/**
 * Criado por danilodequeiroz on 22/10/17.
 */
class RedditSampleTimber : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.ERROR -> t?.let { logError(it) }
            Log.WARN -> Crashlytics.log(message)
        }

        if (priority == Log.ERROR || priority == Log.WARN) {
            Log.println(priority, tag, message)
        }
    }

    private fun logError(err: Throwable?) {
        Crashlytics.logException(err)
    }
}

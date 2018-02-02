package com.dzsamples.reddit.global

import android.util.Log
import timber.log.Timber

/**
 * Criado por danilodequeiroz on 22/10/17.
 */
class RedditSampleTimber : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return super.createStackElementTag(element) + "|" + element.lineNumber
    }

    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        Log.println(priority, "RedditSample >> " + "|" + tag, message)
    }
}
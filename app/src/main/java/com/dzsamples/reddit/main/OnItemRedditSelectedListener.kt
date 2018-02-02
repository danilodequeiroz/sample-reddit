package com.dzsamples.reddit.main

import com.dzsamples.reddit.core.model.NewsData

/**
 * Criado por danilodequeiroz on 22/10/17.
 */
interface OnItemRedditSelectedListener {
    fun onItemSelected(new: NewsData)
}
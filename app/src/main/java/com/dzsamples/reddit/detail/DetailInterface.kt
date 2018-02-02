package com.dzsamples.reddit.detail

import com.dzsamples.reddit.core.global.ErrorViewInterface
import com.dzsamples.reddit.core.global.ProgressViewInterface
import com.dzsamples.reddit.core.model.CommentData

/**
 * Criado por danilodequeiroz on 20/10/17.
 */
interface DetailInterface {

    interface DetailView : ProgressViewInterface, ErrorViewInterface {
        fun loadComments(comments: List<CommentData>)
    }

    interface DetailPresenter {
        fun onViewCreated(id: String, connectedToInternet: Boolean)
        fun onDestroy()
    }
}
package com.dzsamples.reddit.detail

import com.dzsamples.reddit.core.data.DataManagerInterface
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Criado por danilodequeiroz on 20/10/17.
 */
class DetailPresenterImpl(private var detailView: DetailInterface.DetailView, private var dataManager: DataManagerInterface) : DetailInterface.DetailPresenter {
    private val disposables = CompositeDisposable()

    override fun onViewCreated(id: String, connectedToInternet: Boolean) {
        detailView.showProgressBar()
        loadComments(id, connectedToInternet)
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    private fun loadComments(id: String, connectedToInternet: Boolean) {
        val observableComments = if (connectedToInternet) {
            dataManager.getAllCommentsNew(id)
        } else {
            dataManager.getAllCommentsNewLocal(id)
        }

        observableComments.let {
            disposables.add(observableComments
                    .subscribe({ comments ->
                        detailView.loadComments(comments)
                        detailView.hideProgressBar()
                    }, { error ->
                        detailView.error()
                        detailView.hideProgressBar()
                        Timber.w(error)
                    }))
        }
    }
}

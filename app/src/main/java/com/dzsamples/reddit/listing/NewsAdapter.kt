package com.dzsamples.reddit.listing

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.dzsamples.reddit.R
import com.dzsamples.reddit.core.extensions.getTimeString
import com.dzsamples.reddit.core.extensions.loadImage
import com.dzsamples.reddit.core.extensions.sharedLink
import com.dzsamples.reddit.core.model.NewsData
import com.dzsamples.reddit.main.MainActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*
import kotlinx.android.synthetic.main.news_default_layout.*


/**
 * Criado por danilodequeiroz on 19/10/17.
 */
class NewsAdapter(private val activity: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var showLoader: Boolean = false
    private var news = mutableListOf<NewsData>()
    private var newsOriginal = mutableListOf<NewsData>()
    private lateinit var context: AppCompatActivity

    companion object {
        private val VIEW_ITEM = 1
        private val VIEW_LOADER = 2
    }

    fun setNews(news: List<NewsData>) {
        this.newsOriginal.addAll(news)
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context as AppCompatActivity
        return if (viewType == VIEW_ITEM) {
            NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
        } else {
            LoaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.loader_item_layout, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        if (news.size == newsOriginal.size && position != 0 && position == itemCount - 1) {
            return VIEW_LOADER
        }
        return VIEW_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (news.isNotEmpty()) {
            if (getItemViewType(position) == VIEW_ITEM) {
                val itemHolder = holder as NewsViewHolder
                val new = news[position]

                itemHolder.score.text = new.score.toString()
                itemHolder.comments.text = new.numComments.toString()
                itemHolder.share.setOnClickListener { context.sharedLink(new.url) }
                itemHolder.title.text = new.title
                setAuthor(new, itemHolder.author)
                itemHolder.cardView.setOnClickListener { activity.onItemSelected(new) }
                setDescription(new.selftext, itemHolder.description)
                setImage(new.thumbnail, itemHolder.image)
            } else {
                (holder as LoaderViewHolder).progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun setAuthor(new: NewsData, author: TextView) {
        author.text = new.author.plus(" - ").plus(new.created.getTimeString())
    }

    private fun setDescription(selftext: String, description: TextView) {
        if (selftext.isNotEmpty()) {
            description.text = selftext
            description.visibility = View.VISIBLE
        } else {
            description.visibility = View.GONE
        }
    }

    private fun setImage(thumbnail: String, image: ImageView) {
        if (!thumbnail.contains("http") || thumbnail.isEmpty()) {
            image.visibility = View.GONE
        } else {
            image.visibility = View.VISIBLE
            image.loadImage(thumbnail)
        }
    }

    override fun getItemCount(): Int = news.size

    fun showLoading(status: Boolean) {
        showLoader = status
    }

    inner class NewsViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        var author: TextView = author_default
        var title: TextView = title_default
        var image: ImageView = image_item
        var score: TextView = score_default
        var comments: TextView = comment_default
        var share: TextView = share_default
        var description: TextView = description_default
        var cardView: CardView = card_view_new
    }


    inner class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressbar)
    }


    fun filter(text: String?) {
        news.clear()
        text?.let {
            if (it.isEmpty()) {
                news.addAll(newsOriginal)
            } else {
                newsOriginal
                        .filter { it.title.contains(text, true) }
                        .map { news.add(it) }

            }
            notifyDataSetChanged()
        }
    }
}
package com.dzsamples.reddit.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dzsamples.reddit.R
import com.dzsamples.reddit.core.extensions.getTimeString
import com.dzsamples.reddit.core.model.CommentData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_comments.*

/**
 * Criado por danilodequeiroz on 21/10/17.
 */
class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    private var comments = mutableListOf<CommentData>()
    private lateinit var context: Context

    fun setComments(comments: List<CommentData>) {
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        if (comments.isNotEmpty()) {
            val comment = comments[position]
            holder.author.text = "[".plus(comment.author).plus(" - ").plus(comment.created.getTimeString()).plus("]")
            holder.body.text = comment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        this.context = parent.context
        return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comments, parent, false))
    }

    override fun getItemCount(): Int = comments.size


    inner class CommentsViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        var author: TextView = author_comment
        var body: TextView = body_comment
    }
}
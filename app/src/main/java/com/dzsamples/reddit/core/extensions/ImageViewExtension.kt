package com.dzsamples.reddit.core.extensions

import android.widget.ImageView
import com.dzsamples.reddit.R
import com.squareup.picasso.Picasso

/**
 * Criado por danilodequeiroz on 19/10/17.
 */

fun ImageView.loadImage(url: String) {
    Picasso.with(context)
            .load(url)
            .fit()
            .error(R.drawable.image_empty)
            .placeholder(R.drawable.image_empty)
            .into(this)
}
package com.dzsamples.reddit.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dzsamples.reddit.R
import com.dzsamples.reddit.core.extensions.isNotTablet
import com.dzsamples.reddit.core.extensions.isTablet
import com.dzsamples.reddit.core.extensions.showFragment
import com.dzsamples.reddit.core.extensions.startActivityWithSlideTransictionOpenUpActivity
import com.dzsamples.reddit.core.global.ConstantsParceable
import com.dzsamples.reddit.core.model.NewsData
import com.dzsamples.reddit.detail.DetailActivity
import com.dzsamples.reddit.detail.DetailFragment
import com.dzsamples.reddit.listing.ListingFragment

class MainActivity : AppCompatActivity(), OnItemRedditSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (isNotTablet()) {
            showFragment(R.id.fragment_container_list, ListingFragment.newInstance())
        }
    }

    override fun onItemSelected(new: NewsData) {
        if (isTablet()) {
            showFragment(R.id.fragment_container_detail, DetailFragment.newInstance(new))
        } else {
            val detailIntent = Intent(this, DetailActivity::class.java)
            detailIntent.putExtra(ConstantsParceable.SEND_BUNDLE_REDDIT_DATA, new)
            this.startActivityWithSlideTransictionOpenUpActivity(detailIntent)
        }
    }
}

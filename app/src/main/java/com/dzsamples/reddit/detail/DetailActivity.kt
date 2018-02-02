package com.dzsamples.reddit.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.dzsamples.reddit.R
import com.dzsamples.reddit.core.extensions.closeActivityWithSlideTransictionCloseDownActivity
import com.dzsamples.reddit.core.extensions.showFragment
import com.dzsamples.reddit.core.global.ConstantsParceable
import com.dzsamples.reddit.core.model.NewsData

/**
 * Criado por danilodequeiroz on 20/10/17.
 */
class DetailActivity : AppCompatActivity() {
    var animate: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val new: NewsData = intent.extras[ConstantsParceable.SEND_BUNDLE_REDDIT_DATA] as NewsData
        showFragment(R.id.fragment_container_list, DetailFragment.newInstance(new))
    }

    override fun onPause() {
        super.onPause()
        if (animate) {
            this.closeActivityWithSlideTransictionCloseDownActivity()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        animate = true
        super.onBackPressed()
    }
}
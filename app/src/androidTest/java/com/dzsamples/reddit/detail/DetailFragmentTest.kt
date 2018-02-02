package com.dzsamples.reddit.detail

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dzsamples.reddit.initMockServer
import com.dzsamples.reddit.main.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Criado por danilodequeiroz on 22/10/17.
 */
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {
    @get:Rule
    private val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var robo: RoboDetail
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        robo = RoboDetail()
        server = MockWebServer()
        server.initMockServer()
        activityRule.launchActivity(Intent())
    }

    @Test
    fun verify_if_description_was_loaded_completed() {
        robo.clickItem(0)
                .verifyTextComplete("and move on.\n")
    }

    @Test
    fun verify_if_list_comments_was_loaded() {
        robo.clickItem(0)
                .verifyRecyclerViewVisible()
    }
}
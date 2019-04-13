package com.postsapp.android

import androidx.test.rule.ActivityTestRule
import com.postsapp.android.screens.CommentsListScreen
import com.postsapp.android.screens.TabMenu
import com.postsapp.android.screens.UsersListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CommentsListTests {
    private val menuScreen = TabMenu()
    private val commentsListScreen = CommentsListScreen()

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        menuScreen.openCommentsMenu()
    }

    @Test
    fun checkCommentsListLoads() {
        //TODO: handle network delay with Rx Idling Resource or mock server

        commentsListScreen.checkPositionHasText("id labore ex et quam laborum",0)

        commentsListScreen.checkPositionHasText("quo vero reiciendis velit similique earum",1)
    }
}

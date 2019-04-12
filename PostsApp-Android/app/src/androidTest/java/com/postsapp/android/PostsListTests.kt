package com.postsapp.android

import androidx.test.rule.ActivityTestRule
import com.postsapp.android.screens.PostsListScreen
import com.postsapp.android.screens.TabMenu
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostsListTests {
    private val menuScreen = TabMenu()
    private val postsListScreen = PostsListScreen()

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        menuScreen.openPostsMenu()
    }

    @Test
    fun checkPostListLoads() {
        //TODO: handle network delay with Rx Idling Resource or mock server

        postsListScreen.checkPositionHasText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",0)
        postsListScreen.checkPositionHasText("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",0)

        postsListScreen.checkPositionHasText("qui est esse",1)
        postsListScreen.checkPositionHasText("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla",1)
    }
}

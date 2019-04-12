package com.postsapp.android

import androidx.test.rule.ActivityTestRule
import com.postsapp.android.screens.PostDetailScreen
import com.postsapp.android.screens.PostsListScreen
import com.postsapp.android.screens.TabMenu
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostDetailTests {
    private val menuScreen = TabMenu()
    private val postsListScreen = PostsListScreen()
    private val postDetailsScreen = PostDetailScreen()

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        menuScreen.openPostsMenu()
        postsListScreen.clickPosition(0)
    }

    @Test
    fun checkPostDetailsLoad() {
        //TODO: handle network delay with Rx Idling Resource or mock server

        postDetailsScreen.checkTitleHasText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
        postDetailsScreen.checkBodyHasText("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto")
        postDetailsScreen.checkNumberOfCommentsHasText("5")
        postDetailsScreen.checkAuthorNameHasText("Leanne Graham")

    }
}

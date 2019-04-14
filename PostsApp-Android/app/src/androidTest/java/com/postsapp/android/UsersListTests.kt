package com.postsapp.android

import androidx.test.rule.ActivityTestRule
import com.postsapp.android.screens.TabMenu
import com.postsapp.android.screens.UsersListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsersListTests {
    private val menuScreen = TabMenu()
    private val usersListScreen = UsersListScreen()

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        menuScreen.openUsersMenu()
    }

    @Test
    fun checkUsersListLoads() {
        //TODO: handle network delay with Rx Idling Resource or mock server

        usersListScreen.checkPositionHasText("Leanne Graham", 0)
        usersListScreen.checkPositionHasText("Ervin Howell", 1)
    }
}

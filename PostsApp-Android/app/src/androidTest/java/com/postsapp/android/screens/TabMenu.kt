package com.postsapp.android.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.postsapp.android.R

internal class TabMenu {
    fun openPostsMenu() {
        onView(withId(R.id.postsListFragment)).perform(click())
    }

    fun openUsersMenu() {
        onView(withId(R.id.usersListFragment)).perform(click())
    }

    fun openCommentsMenu() {
        onView(withId(R.id.commentsListFragment)).perform(click())
    }
}
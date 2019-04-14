package com.postsapp.android.screens

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import com.postsapp.android.R
import com.postsapp.android.ui.postslist.PostsListAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher

internal class PostsListScreen {
    private val postsList = R.id.postsListRecyclerView

    fun checkPositionHasText(text: String, position: Int) {
        checkTextOnRecycler(postsList, text, position)
    }

    fun clickPosition(position: Int) {
        clickItemOnRecycler(postsList, position)
    }
}

fun clickItemOnRecycler(recyclerId: Int, position: Int) {
    onView(withId(recyclerId))
        .perform(RecyclerViewActions.actionOnItemAtPosition<PostsListAdapter.ViewHolder>(position, click()))
}

fun checkTextOnRecycler(recyclerId: Int, text: String, position: Int) {
    onView(withId(recyclerId))
        .check(matches(atPosition(position, hasDescendant(withText(text)))))
}

fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                ?: // has no item on such position
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}

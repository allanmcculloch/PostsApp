package com.postsapp.android.screens

import androidx.test.espresso.matcher.ViewMatchers
import com.postsapp.android.R
import com.postsapp.android.testhelpers.checkHasText
import com.postsapp.android.testhelpers.click
import com.postsapp.android.testhelpers.waitForMatcher
import org.hamcrest.Matchers

internal class PostDetailScreen {
    private val title = R.id.post_detail_title
    private val body = R.id.post_detail_body
    private val numberOfComments = R.id.numberOfComments
    private val authorName = R.id.authorName
    private val commentsButton = R.id.viewCommentsButton

    fun checkTitleHasText(text : String) {
        title.checkHasText(text)
    }

    fun checkBodyHasText(text : String) {
        body.checkHasText(text)
    }

    fun checkNumberOfCommentsHasText(text : String) {
        numberOfComments.checkHasText(text)
    }

    fun checkAuthorNameHasText(text : String) {
        authorName.checkHasText(text)
    }

    fun clickCommentsButton() {
        commentsButton.click()
    }

    fun hasLoadedAndNameHasText(text: String)
    {
        title.waitForMatcher(
            Matchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.withText(Matchers.containsString(text))
            ))
    }
}
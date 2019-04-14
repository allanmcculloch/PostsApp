package com.postsapp.android.screens

import com.postsapp.android.R

internal class CommentsListScreen {
    private val commentsList = R.id.commentsListRecyclerView

    fun checkPositionHasText(text: String, position: Int) {
        checkTextOnRecycler(commentsList, text, position)
    }
}
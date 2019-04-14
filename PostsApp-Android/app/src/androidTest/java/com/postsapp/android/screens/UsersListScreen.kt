package com.postsapp.android.screens

import com.postsapp.android.R

internal class UsersListScreen {
    private val usersList = R.id.usersListRecyclerView

    fun checkPositionHasText(text: String, position: Int) {
        checkTextOnRecycler(usersList, text, position)
    }
}
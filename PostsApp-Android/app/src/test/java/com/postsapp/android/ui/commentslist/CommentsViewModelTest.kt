package com.postsapp.android.ui.userslist

import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.model.User
import com.jraska.livedata.test
import com.postsapp.android.model.Comment
import com.postsapp.android.ui.commentslist.CommentViewModel
import org.junit.Test

class CommentsViewModelTest : BaseViewModelTest() {
    @Test
    fun testReturnsCorrectValues() {

        val viewModel = createViewModel()

        viewModel.bind(commentTestData)

        viewModel.name.test().assertValue(commentTestData.name)
        viewModel.email.test().assertValue(commentTestData.email)
        viewModel.body.test().assertValue(commentTestData.body)
        viewModel.imageUrl.test().assertValue("https://api.adorable.io/avatars/285/${commentTestData.name}.png")
    }

    private fun createViewModel() = CommentViewModel()

    private val commentTestData = Comment(1, 1234,"name1","email1@email", "body1")
}
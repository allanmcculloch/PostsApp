package com.postsapp.android.ui.postslist

import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.model.Post
import com.jraska.livedata.test
import com.postsapp.android.ui.postslist.PostViewModel
import org.junit.Test

class PostViewModelTest : BaseViewModelTest() {
    private val postTestData = Post(
        1,
        1,
        "title1",
        "body1"
    )

    @Test
    fun testReturnsCorrectValues() {

        val viewModel = createViewModel()

        viewModel.bind(postTestData)

        viewModel.body.test().assertValue(postTestData.body)
        viewModel.title.test().assertValue(postTestData.title)
    }

    private fun createViewModel() = PostViewModel()
}
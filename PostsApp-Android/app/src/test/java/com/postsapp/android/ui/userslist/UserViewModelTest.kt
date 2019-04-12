package com.postsapp.android.ui.userslist

import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.model.User
import com.jraska.livedata.test
import org.junit.Test

class UserViewModelTest : BaseViewModelTest() {
    @Test
    fun testReturnsCorrectValues() {

        val viewModel = createViewModel()

        viewModel.bind(userTestData)

        viewModel.userName.test().assertValue(userTestData.name)
        viewModel.imageUrl.test().assertValue("https://api.adorable.io/avatars/285/${userTestData.id}.png")
    }

    private fun createViewModel() = UserViewModel()

    private val userTestData = User(1, "user1")
}
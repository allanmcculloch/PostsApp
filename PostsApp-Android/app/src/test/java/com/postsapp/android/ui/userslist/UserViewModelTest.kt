package com.postsapp.android.ui.userslist

import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.model.User
import com.jraska.livedata.test
import com.postsapp.android.model.Company
import org.junit.Test

class UserViewModelTest : BaseViewModelTest() {
    @Test
    fun testReturnsCorrectValues() {

        val viewModel = createViewModel()

        viewModel.bind(userTestData)

        viewModel.userName.test().assertValue(userTestData.name)
        viewModel.imageUrl.test().assertValue("https://api.adorable.io/avatars/285/${userTestData.id}.png")
        viewModel.email.test().assertValue(userTestData.email)
        viewModel.company.test().assertValue(userTestData.company?.name)
    }

    private fun createViewModel() = UserViewModel()

    private val userTestData = User(1, "user1", "email@email.com",company = Company("some company","catchphrase","bs"))
}
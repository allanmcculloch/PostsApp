package com.postsapp.android.ui.userslist

import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.usecases.GetUsersUseCase
import com.postsapp.android.model.User
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UsersListViewModelTest : BaseViewModelTest() {
    lateinit var viewModel: UsersListViewModel
    lateinit var getUsersUseCase: GetUsersUseCase

    private val sampleData =
        listOf(
            User(1, "user1"),
            User(2, "user2")
        )

    @Before
    fun setUp() {
        getUsersUseCase = mockk(relaxed = true)

        every { getUsersUseCase.execute() }.returns(Observable.just(sampleData))
    }

    @Test
    fun retunsCorrectNumberOfRows() {

        viewModel = createViewModel()

        assertEquals(viewModel.usersListAdaptor.itemCount, sampleData.count())
    }

    private fun createViewModel() = UsersListViewModel(getUsersUseCase)
}

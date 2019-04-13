package com.postsapp.android.ui.userslist

import com.postsapp.android.model.Comment
import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.usecases.GetUsersUseCase
import com.postsapp.android.model.User
import com.postsapp.android.ui.commentslist.CommentsListViewModel
import com.postsapp.android.usecases.GetCommentsByPostIdUseCase
import com.postsapp.android.usecases.GetCommentsUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CommentsListViewModelTest : BaseViewModelTest() {
    lateinit var viewModel : CommentsListViewModel
    lateinit var getCommentsUseCase : GetCommentsUseCase
    lateinit var getCommentsByPostIdUseCase: GetCommentsByPostIdUseCase

    @Before
    fun setUp() {
        getCommentsUseCase = mockk(relaxed = true)
        getCommentsByPostIdUseCase = mockk(relaxed = true)

        every {getCommentsUseCase.execute() }.returns(Observable.just(sampleData))
    }

    @Test
    fun retunsCorrectNumberOfRows() {

        viewModel = createViewModel()

        assertEquals(viewModel.commentsListAdapter.itemCount, sampleData.count())
    }

    private fun createViewModel() = CommentsListViewModel(getCommentsUseCase, getCommentsByPostIdUseCase)

    private val sampleData =

        listOf(
            Comment(1, 1234,"name1","email1@email", "body1"),
            Comment(2, 1235, "name2","email2@email", "body2")
        )
}

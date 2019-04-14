package com.postsapp.android.ui.userslist

import com.postsapp.android.model.Comment
import com.postsapp.android.ui.BaseViewModelTest
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
    fun whenNoPostIdSupplied_returnsCorrectNumberOfRows() {
        viewModel = createViewModel()

        viewModel.loadData(null)

        assertEquals(sampleData.count(), viewModel.commentsListAdapter.itemCount)
    }

    @Test
    fun whenPostIdSupplied_returnsCorrectNumberOfRows() {
        val postIdToTest = 2

        every {getCommentsByPostIdUseCase.execute(postIdToTest) }.returns(Observable.just(sampleData.filter { p -> p.postId == postIdToTest }))

        viewModel = createViewModel()

        viewModel.loadData(postIdToTest)

        assertEquals(sampleData.count { r -> r.postId == postIdToTest}, viewModel.commentsListAdapter.itemCount)
    }

    private fun createViewModel() = CommentsListViewModel(getCommentsUseCase, getCommentsByPostIdUseCase)

    private val sampleData =
        listOf(
            Comment(1, 1234,"name1","email1@email", "body1"),
            Comment(2, 1235, "name2","email2@email", "body2"),
            Comment(2, 1236, "name3","email3@email", "body3"),
            Comment(3, 1236, "name4","email4@email", "body4")
        )
}

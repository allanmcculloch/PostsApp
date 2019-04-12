package com.postsapp.android.ui.postslist

import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.usecases.GetPostsListUseCase
import com.postsapp.android.model.Post
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PostListViewModelTest : BaseViewModelTest() {
    lateinit var viewModel : PostsListViewModel
    lateinit var getPostsListUseCase : GetPostsListUseCase

    @Before
    fun setUp() {
        getPostsListUseCase = mockk(relaxed = true)

        every {getPostsListUseCase.execute() }.returns(Observable.just(sampleData))
    }

    @Test
    fun returnsCorrectNumberOfRows() {
        viewModel = createViewModel()

        assertEquals(viewModel.postsListAdapter.itemCount, sampleData.count())
    }

    private fun createViewModel() = PostsListViewModel(getPostsListUseCase)

    private val sampleData =
        listOf(
            Post(
                1,
                1,
                "Description1",
                "period1"
            ),
            Post(
                2,
                2,
                "Description2",
                "period2"
            )
        )
}

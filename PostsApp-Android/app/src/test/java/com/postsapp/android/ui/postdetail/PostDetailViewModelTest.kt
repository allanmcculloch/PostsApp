package com.postsapp.android.ui.postdetail

import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.usecases.GetPostUseCase
import com.jraska.livedata.test
import com.postsapp.android.model.Post
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class PostDetailViewModelTest : BaseViewModelTest() {
    lateinit var viewModel : PostDetailViewModel
    lateinit var getPostUseCase: GetPostUseCase

    @Before
    fun setUp() {
        getPostUseCase = mockk(relaxed = true)

        every {getPostUseCase.execute(testPost.id) }.returns(Observable.just(testPost))
    }

    @Test
    fun checkFields() {
        viewModel = createViewModel()

        viewModel.loadData(testPost.id)

        viewModel.title.test()
            .awaitValue()
            .assertHasValue()
            .assertValue(testPost.title)

        viewModel.body.test()
            .awaitValue()
            .assertHasValue()
            .assertValue(testPost.body)
    }

    private fun createViewModel() = PostDetailViewModel(getPostUseCase)

    private val testPost : Post =
        Post(
            11,
            11,
            "Some title",
            "Some body"
        )
}
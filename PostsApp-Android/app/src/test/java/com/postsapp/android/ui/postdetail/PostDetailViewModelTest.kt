package com.postsapp.android.ui.postdetail

import com.jraska.livedata.test
import com.postsapp.android.model.PostDetail
import com.postsapp.android.ui.BaseViewModelTest
import com.postsapp.android.usecases.GetPostDetailUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class PostDetailViewModelTest : BaseViewModelTest() {
    lateinit var viewModel : PostDetailViewModel
    lateinit var getPostDetailUseCase: GetPostDetailUseCase

    @Before
    fun setUp() {
        getPostDetailUseCase = mockk(relaxed = true)

        every {getPostDetailUseCase.execute(testPost.id) }.returns(Observable.just(testPost))
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

        viewModel.numberOfComments.test()
            .awaitValue()
            .assertHasValue()
            .assertValue(testPost.numberOfComments.toString())

        viewModel.authorName.test()
            .awaitValue()
            .assertHasValue()
            .assertValue(testPost.userName)
    }

    private fun createViewModel() = PostDetailViewModel(getPostDetailUseCase)

    private val testPost : PostDetail =
        PostDetail(
            11,
            11,
            "Some title",
            "Some body",
            4,
            "someUser"
        )
}
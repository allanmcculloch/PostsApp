package com.postsapp.android.ui.commentslist

import androidx.lifecycle.ViewModel
import com.postsapp.android.model.Comment
import com.postsapp.android.usecases.GetCommentsByPostIdUseCase
import com.postsapp.android.usecases.GetCommentsUseCase
import com.postsapp.android.usecases.UseCase
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class CommentsListViewModel(private val getCommentsUseCase: GetCommentsUseCase, private val getCommentsByPostIdUseCase: GetCommentsByPostIdUseCase) : ViewModel() {
    val commentsListAdapter: CommentsListAdapter = CommentsListAdapter()

    private lateinit var subscription: Disposable

    fun loadData(postIdFilter : Int?) {
        if (postIdFilter != null)
            loadWithFilter(postIdFilter)
        else
            loadWithoutFilter()
    }

    private fun loadWithFilter(postIdFilter: Int) {
        subscription =
            getCommentsByPostIdUseCase
                .execute(postIdFilter)
                .subscribe({
                    onFetchedList(it)
                }, Throwable::printStackTrace)
    }

    private fun loadWithoutFilter() {
        subscription =
            getCommentsUseCase
                .execute()
                .subscribe({
                    onFetchedList(it)
                }, Throwable::printStackTrace)
    }

    private fun onFetchedList(commentsList : List<Comment>) {
        commentsListAdapter.updateList(commentsList)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

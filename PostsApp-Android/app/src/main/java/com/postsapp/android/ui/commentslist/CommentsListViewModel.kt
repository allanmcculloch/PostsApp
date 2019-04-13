package com.postsapp.android.ui.commentslist

import androidx.lifecycle.ViewModel
import com.postsapp.android.model.Comment
import com.postsapp.android.usecases.GetCommentsByPostIdUseCase
import com.postsapp.android.usecases.GetCommentsUseCase
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class CommentsListViewModel(private val getCommentsUseCase: GetCommentsUseCase, private val getCommentsByPostIdUseCase: GetCommentsByPostIdUseCase) : ViewModel() {

    val commentsListAdapter: CommentsListAdapter = CommentsListAdapter()

    private lateinit var subscription: Disposable

    init {
        loadData()
    }

    private fun loadData() {
        subscription = getCommentsUseCase
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

package com.postsapp.android.ui.postslist

import androidx.lifecycle.ViewModel
import com.postsapp.android.usecases.GetPostsListUseCase
import com.postsapp.android.model.Post
import com.postsapp.android.ui.postslist.PostsListAdapter
import io.reactivex.disposables.Disposable

class PostsListViewModel(private val getPostsListUseCase: GetPostsListUseCase) : ViewModel() {
    val postsListAdapter: PostsListAdapter = PostsListAdapter()

    private lateinit var subscription: Disposable

    init {
        loadData()
    }

    private fun loadData() {
        subscription = getPostsListUseCase
            .execute()
            .subscribe({
            onFetchedList(it)
        }, Throwable::printStackTrace)
    }

    private fun onFetchedList(postList : List<Post>) {
        postsListAdapter.updateList(postList)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

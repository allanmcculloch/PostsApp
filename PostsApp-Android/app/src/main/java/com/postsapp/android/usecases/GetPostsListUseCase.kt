package com.postsapp.android.usecases

import com.postsapp.android.model.Post
import com.postsapp.android.repository.PostsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetPostsListUseCase(private val repository: PostsRepository) :
    ObservableWithoutParamUseCase<List<Post>>(
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    ) {
    override fun build(): Observable<List<Post>> {
        return repository.getPosts()
    }
}
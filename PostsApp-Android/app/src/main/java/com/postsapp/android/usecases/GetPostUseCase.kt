package com.postsapp.android.usecases

import com.postsapp.android.model.Post
import com.postsapp.android.repository.PostsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetPostUseCase(
    private val repository: PostsRepository
) :
    ObservableWithParamUseCase<Post, Int>(
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    ) {
    override fun build(param: Int): Observable<Post> {
        return repository.getPost(param)
    }
}
package com.postsapp.android.usecases

import com.postsapp.android.repository.PostsRepository
import com.postsapp.android.model.Post
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetPostsListUseCase(private val repository: PostsRepository, threadExecuter: Scheduler = Schedulers.io()) :
    ObservableWithoutParamUseCase<List<Post>>(
        threadExecuter,
        AndroidSchedulers.mainThread()
    ) {
    override fun build(): Observable<List<Post>> {
        return repository.getPosts()
    }
}
package com.postsapp.android.usecases

import com.postsapp.android.model.Comment
import com.postsapp.android.repository.CommentsRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCommentsByPostIdUseCase(
    private val repository: CommentsRepository,
    threadExecuter: Scheduler = Schedulers.io()
) :
    ObservableWithParamUseCase<List<Comment>, Int>(
        threadExecuter,
        AndroidSchedulers.mainThread()
    ) {
    override fun build(param : Int): Observable<List<Comment>> {
        return repository.getCommentsByPost(param)
    }
}
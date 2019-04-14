package com.postsapp.android.usecases

import com.postsapp.android.model.Comment
import com.postsapp.android.repository.CommentsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCommentsUseCase(
    private val repository: CommentsRepository
) :
    ObservableWithoutParamUseCase<List<Comment>>(
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    ) {
    override fun build(): Observable<List<Comment>> {
        return repository.getComments()
    }
}
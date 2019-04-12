package com.postsapp.android.usecases

import com.postsapp.android.repository.UsersRepository
import com.postsapp.android.model.User
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetUsersUseCase(private val repository: UsersRepository, threadExecuter: Scheduler = Schedulers.io()) :
    ObservableWithoutParamUseCase<List<User>>(
        threadExecuter,
        AndroidSchedulers.mainThread()
    ) {
    override fun build(): Observable<List<User>> {
        return repository.getUsers()
    }
}
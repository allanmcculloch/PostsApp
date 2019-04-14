package com.postsapp.android.usecases

import com.postsapp.android.model.User
import com.postsapp.android.repository.UsersRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetUsersUseCase(private val repository: UsersRepository) :
    ObservableWithoutParamUseCase<List<User>>(
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    ) {
    override fun build(): Observable<List<User>> {
        return repository.getUsers()
    }
}
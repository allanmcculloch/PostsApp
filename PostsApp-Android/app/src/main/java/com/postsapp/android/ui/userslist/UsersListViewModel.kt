package com.postsapp.android.ui.userslist

import androidx.lifecycle.ViewModel
import com.postsapp.android.usecases.GetUsersUseCase
import com.postsapp.android.model.User

import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class UsersListViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {
    val usersListAdaptor: UsersListAdapter = UsersListAdapter()
    private lateinit var subscription: Disposable

    init {
        loadData()
    }

    private fun loadData() {
        subscription = getUsersUseCase.execute()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe({
                onFetchedList(it)
            },Throwable::printStackTrace)
    }

    private fun onFetchedList(users : List<User>) {
        usersListAdaptor.updateList(users)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

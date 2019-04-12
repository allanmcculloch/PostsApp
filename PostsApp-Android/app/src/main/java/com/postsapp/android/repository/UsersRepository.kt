package com.postsapp.android.repository

import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.model.User
import io.reactivex.Observable

class UsersRepository(private val postsApiService : PostsApiService) {
    var cache : List<User> = listOf()

    fun getUsers() : Observable<List<User>> {
        if (cache.isEmpty()) {
            return postsApiService.getUsers()
                .doOnNext { cache = it }
        }
        else {
            return Observable.just(cache)
                .mergeWith(postsApiService.getUsers())
                .doOnNext { cache = it  }
        }
    }
}
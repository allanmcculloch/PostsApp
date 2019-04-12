package com.postsapp.android.repository

import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.model.User
import io.reactivex.Observable

class UsersRepository(private val postsApiService : PostsApiService) {
    var _cache : MutableList<User> = mutableListOf()
    var cache : List<User>
        get() {
            return _cache
        }
        set(value) {
            _cache = value.toMutableList()
        }

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

    fun getUser(id : Int) : Observable<User> {
        if (_cache.isEmpty() || ! _cache.any { r -> r.id == id }) {
            return postsApiService.getUser(id)
                .doOnNext { _cache.add(it) }
        }
        else {
            var cachedUser = _cache.first { r -> r.id == id }

            return Observable.just(cachedUser)
                .mergeWith(postsApiService.getUser(id))
                .doOnNext { cachedUser = it }
        }
    }
}
package com.postsapp.android.repository

import com.postsapp.android.model.User
import com.postsapp.android.repository.remote.PostsApiService
import io.reactivex.Observable

class UsersRepository(private val postsApiService: PostsApiService) {
    private val errorUser = User(-1, "Unknown")
    private var _cache: MutableList<User> = mutableListOf()

    var cache: List<User>
        get() {
            return _cache
        }
        set(value) {
            _cache = value.toMutableList()
        }

    fun getUsers(): Observable<List<User>> {
        if (cache.isEmpty()) {
            return postsApiService.getUsers()
                .doOnNext { cache = it }
        } else {
            return Observable.just(cache)
                .mergeWith(postsApiService.getUsers().onErrorResumeNext(Observable.just(cache)))
                .doOnNext { cache = it }
        }
    }

    fun getUser(id: Int): Observable<User> {
        if (_cache.isEmpty() || !_cache.any { r -> r.id == id }) {
            return postsApiService.getUser(id).onErrorResumeNext(Observable.just(errorUser))
                .doOnNext {
                    if (it != errorUser)
                        _cache.add(it)
                }
        } else {
            return Observable.just(_cache.first { r -> r.id == id })
        }
    }
}
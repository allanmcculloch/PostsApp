package com.postsapp.android.repository

import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.model.Post
import io.reactivex.Observable

class PostsRepository(private val postsApiService : PostsApiService) {

    private var _cache : MutableList<Post> = mutableListOf()
    var cache : List<Post>
        get() {
            return _cache
        }
        set(value) {
            _cache = value.toMutableList()
        }

    fun getPosts() : Observable<List<Post>> {
        if (_cache.isEmpty()) {
            return postsApiService.getPosts()
                .doOnNext { _cache = it.toMutableList() }
        }
        else {
            return Observable.just(cache)
                .mergeWith(postsApiService.getPosts())
                .doOnNext { _cache = it.toMutableList() }

        }
    }

    fun getPost(id : Int) : Observable<Post> {
        if (_cache.isEmpty() || ! _cache.any { r -> r.id == id }) {
            return postsApiService.getPost(id)
                .doOnNext { _cache.add(it) }
        }
        else {
            var cachedPost = _cache.first { r -> r.id == id }

            return Observable.just(cachedPost)
                .mergeWith(postsApiService.getPost(id))
                .doOnNext { cachedPost = it }
        }
    }
}
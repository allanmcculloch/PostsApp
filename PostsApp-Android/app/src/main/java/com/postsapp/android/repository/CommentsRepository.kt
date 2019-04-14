package com.postsapp.android.repository

import com.postsapp.android.model.Comment
import com.postsapp.android.repository.remote.PostsApiService
import io.reactivex.Observable

class CommentsRepository(private val postsApiService : PostsApiService) {
    var cache : MutableMap<Int,List<Comment>> = mutableMapOf()

    fun getCommentsByPost(postId: Int) : Observable<List<Comment>> {
        if (cache[postId] == null || cache[postId]?.isEmpty() == true) {
            return postsApiService.getCommentsByPost(postId).onErrorReturnItem(listOf())
                .doOnNext { cache[postId] = it }
        }
        else {
            return Observable.just(cache[postId]!!)
                .mergeWith(postsApiService.getCommentsByPost(postId).onErrorResumeNext(Observable.just(cache[postId]!!)))
                .doOnNext { cache[postId] = it  }
        }
    }

    fun getComments() : Observable<List<Comment>> {
        val cachedFullCommentsList = cache.values.flatMap { it.toList() }

        return Observable.just(cachedFullCommentsList)
            .mergeWith(postsApiService.getComments().onErrorResumeNext(Observable.just(cachedFullCommentsList)))
            .doOnNext {
                cache = it.groupBy { p -> p.postId }.toMutableMap()
            }
    }
}
package com.postsapp.android.repository

import com.postsapp.android.model.Comment
import com.postsapp.android.repository.remote.PostsApiService
import io.reactivex.Observable

class CommentsRepository(private val postsApiService : PostsApiService) {
    var cache : List<Comment> = listOf()

    fun getComments(postId: Int) : Observable<List<Comment>> {
        if (cache.isEmpty()) {
            return postsApiService.getComments(postId)
                .doOnNext { cache = it }
        }
        else {
            return Observable.just(cache)
                .mergeWith(postsApiService.getComments(postId))
                .doOnNext { cache = it  }
        }
    }
}
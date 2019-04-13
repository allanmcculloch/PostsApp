package com.postsapp.android.repository

import com.postsapp.android.model.Comment
import com.postsapp.android.repository.remote.PostsApiService
import io.reactivex.Observable

class CommentsRepository(private val postsApiService : PostsApiService) {
    var cache : HashMap<Int,List<Comment>> = hashMapOf()

    fun getCommentsByPost(postId: Int) : Observable<List<Comment>> {
        if (cache[postId] == null || cache[postId]?.isEmpty() == true) {
            return postsApiService.getCommentsByPost(postId)
                .doOnNext { cache[postId] = it }
        }
        else {
            return Observable.just(cache[postId]!!)
                .mergeWith(postsApiService.getCommentsByPost(postId))
                .doOnNext { cache[postId] = it  }
        }
    }

    fun getComments() : Observable<List<Comment>> {
        return postsApiService.getComments()
    }
}
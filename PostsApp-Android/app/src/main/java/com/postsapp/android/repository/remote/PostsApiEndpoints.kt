package com.postsapp.android.repository.remote

import com.postsapp.android.model.Comment
import com.postsapp.android.model.Post
import com.postsapp.android.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApiEndpoints {
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/posts/{id}")
    fun getPost(@Path("id") id: Int): Observable<Post>

    @GET("/users")
    fun getUsers(): Observable<List<User>>

    @GET("/users/{id}")
    fun getUser(@Path("id") id: Int): Observable<User>

    @GET("/comments")
    fun getCommentsByPost(@Query("postId") postId: Int): Observable<List<Comment>>

    @GET("/comments")
    fun getComments(): Observable<List<Comment>>
}
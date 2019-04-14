package com.postsapp.android.repository.remote

import com.postsapp.android.model.Comment
import com.postsapp.android.network.ClientProvider
import com.postsapp.android.model.Post
import com.postsapp.android.model.User
import io.reactivex.Observable

class PostsApiService(clientProvider: ClientProvider) {
    private val api = clientProvider.client.create(PostsApiEndpoints::class.java)

    fun getPosts(): Observable<List<Post>> = api.getPosts()

    fun getPost(id: Int): Observable<Post> = api.getPost(id)

    fun getUsers(): Observable<List<User>> = api.getUsers()

    fun getUser(id: Int): Observable<User> = api.getUser(id)

    fun getCommentsByPost(postId: Int): Observable<List<Comment>> = api.getCommentsByPost(postId)

    fun getComments(): Observable<List<Comment>> = api.getComments()
}
package com.postsapp.android.usecases

import com.postsapp.android.model.Comment
import com.postsapp.android.model.Post
import com.postsapp.android.model.PostDetail
import com.postsapp.android.model.User
import com.postsapp.android.repository.CommentsRepository
import com.postsapp.android.repository.PostsRepository
import com.postsapp.android.repository.UsersRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class GetPostDetailUseCase(
    private val postRepository: PostsRepository,
    private val commentsRepository: CommentsRepository,
    private val usersRepository: UsersRepository,

    threadExecuter: Scheduler = Schedulers.io()
) :
    ObservableWithParamUseCase<PostDetail, Int>(
        threadExecuter,
        AndroidSchedulers.mainThread()
    ) {
    override fun build(id: Int): Observable<PostDetail> {
        return Observable.zip(
            postRepository.getPost(id).flatMap(
                { post: Post -> usersRepository.getUser(post.userId) },
                { post: Post, user: User -> Pair(user, post) }
            ),
            commentsRepository.getCommentsByPost(id),
            BiFunction { userPost, comments -> convert(userPost, comments) }
        )
    }

    private fun convert(userPost: Pair<User, Post>, comments: List<Comment>): PostDetail {
        val user = userPost.first
        val post = userPost.second

        return PostDetail(user.id, post.id, post.title, post.body, comments.count(), user.name)
    }
}
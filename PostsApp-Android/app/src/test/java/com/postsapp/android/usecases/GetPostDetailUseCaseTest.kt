package com.postsapp.android.usecases

import com.postsapp.android.model.Comment
import com.postsapp.android.model.Post
import com.postsapp.android.model.User
import com.postsapp.android.repository.CommentsRepository
import com.postsapp.android.repository.PostsRepository
import com.postsapp.android.repository.UsersRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPostDetailUseCaseTest {
    lateinit var postsRepository: PostsRepository
    lateinit var usersRepository: UsersRepository
    lateinit var commentsRepository: CommentsRepository

    private val examplePostId = 423
    private val exampleUserId = 8131
    private val postExample = Post(exampleUserId, examplePostId, "testTitle", "testBody")
    private val userExample = User(exampleUserId, "testUser")
    private val commentsExample = listOf(
        Comment(examplePostId, 1, "name1", "email1", "body1"),
        Comment(examplePostId, 2, "name2", "email2", "body2"),
        Comment(examplePostId, 3, "name3", "email3", "body3"),
        Comment(examplePostId, 4, "name4", "email4", "body4")
    )

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        postsRepository = mockk()
        usersRepository = mockk()
        commentsRepository = mockk()

        every { postsRepository.getPost(examplePostId) }.returns(Observable.just(postExample))
        every { usersRepository.getUser(exampleUserId) }.returns(Observable.just(userExample))
        every { commentsRepository.getCommentsByPost(examplePostId) }.returns(Observable.just(commentsExample))
    }

    @Test
    fun `post detail post data correct`() {
        val service = createService()
        var output = service.execute(examplePostId)
        val postDetails = output.blockingFirst()

        assertEquals(postExample.body, postDetails.body)
        assertEquals(postExample.title, postDetails.title)
    }

    @Test
    fun `post detail number of comments correct`() {
        val service = createService()
        var output = service.execute(examplePostId)
        val postDetails = output.blockingFirst()

        assertEquals(commentsExample.count(), postDetails.numberOfComments)
    }

    @Test
    fun `post detail user correct`() {
        val service = createService()
        var output = service.execute(examplePostId)
        val postDetails = output.blockingFirst()

        assertEquals(userExample.name, postDetails.userName)
    }

    private fun createService(): GetPostDetailUseCase =
        GetPostDetailUseCase(postsRepository, commentsRepository, usersRepository)
}
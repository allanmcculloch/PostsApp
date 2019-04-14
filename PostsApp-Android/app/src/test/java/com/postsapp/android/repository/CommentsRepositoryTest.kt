package com.postsapp.android.repository

import com.postsapp.android.model.Comment
import com.postsapp.android.repository.remote.PostsApiService
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CommentsRepositoryTest {
    private lateinit var postsApiServiceMock: PostsApiService
    private lateinit var commentsRepository : CommentsRepository

    private val samplePostId = 1

    @Before
    fun setup() {
        postsApiServiceMock = mockk(relaxed = true)
        commentsRepository = CommentsRepository(postsApiServiceMock)
    }

    @Test
    fun getCommentsByPost_when_emptycache_callapi_returnsApiData() {
        every { postsApiServiceMock.getCommentsByPost(samplePostId) }.returns(Observable.just(sampleDataApi))

        val commentsReturned = commentsRepository.getCommentsByPost(samplePostId)

        commentsReturned.test().assertValue { it == sampleDataApi }
    }

    @Test
    fun getCommentsByPost_when_hasCache_returnsCache_thenApi() {
        every { postsApiServiceMock.getCommentsByPost(samplePostId) }.returns(Observable.just(sampleDataApi))

        commentsRepository.cache = hashMapOf(samplePostId to sampleDataInCachePost1)

        val commentsReturned = commentsRepository.getCommentsByPost(samplePostId)

        commentsReturned.test().assertValueAt(0) { it == sampleDataInCachePost1}
        commentsReturned.test().assertValueAt(1) { it == sampleDataApi}
    }

    @Test
    fun getCommentsByPost_when_nocache_updatesCacheFromApi() {
        commentsRepository.cache = hashMapOf()

        every { postsApiServiceMock.getCommentsByPost(samplePostId) }.returns(Observable.just(sampleDataApi))

        val commentsReturned = commentsRepository.getCommentsByPost(samplePostId)

        commentsReturned.test().assertValueAt(0) { it == sampleDataApi}

        assertEquals(commentsRepository.cache[samplePostId], sampleDataApi)
    }

    @Test
    fun getComments_when_nocache_updatesCacheFromApi() {
        commentsRepository.cache = hashMapOf()

        every { postsApiServiceMock.getComments() }.returns(Observable.just(sampleDataApi))

        val commentsReturned = commentsRepository.getComments()

        commentsReturned.test().assertValueAt(0) { it == sampleDataApi}

        assertEquals(commentsRepository.cache[samplePostId], sampleDataApi)
    }

    @Test
    fun getComments_when_cache_returnsCacheThenApi() {
        commentsRepository.cache = hashMapOf(1 to sampleDataInCachePost1)

        every { postsApiServiceMock.getComments() }.returns(Observable.just(sampleDataApi))

        val commentsReturned = commentsRepository.getComments()

        commentsReturned.test().assertValueAt(0) { it == sampleDataInCachePost1}
        commentsReturned.test().assertValueAt(1) { it == sampleDataApi}

        assertEquals(commentsRepository.cache[samplePostId], sampleDataApi)
    }

    @Test
    fun getComments_when_cache_returnsCommentsForAllPosts() {
        commentsRepository.cache = hashMapOf(1 to sampleDataInCachePost1, 2 to sampleDataInCachePost2)

        every { postsApiServiceMock.getComments() }.returns(Observable.just(sampleDataApi))

        val commentsReturned = commentsRepository.getComments()

        commentsReturned.test().assertValueAt(0) { it.containsAll(sampleDataInCachePost1) }
        commentsReturned.test().assertValueAt(0) { it.containsAll(sampleDataInCachePost2) }

        commentsReturned.test().assertValueAt(1) { it == sampleDataApi}

        assertEquals(commentsRepository.cache[samplePostId], sampleDataApi)
    }

    private val sampleDataApi =
        listOf(
            Comment(1,1,"name1","email1","body1"),
            Comment(1,2,"name2","email2","body2"),
            Comment(1,3,"name3","email3","body3")

        )

    private val sampleDataInCachePost1 =
        listOf(
            Comment(1,4,"name4","email4","body4")
        )

    private val sampleDataInCachePost2 =
        listOf(
            Comment(2,5,"name4","email4","body4")
        )
}

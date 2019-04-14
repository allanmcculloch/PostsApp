package com.postsapp.android.repository

import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.model.Post
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.internal.verification.Times

class PostsRepositoryTest {
    private lateinit var postsApiServiceMock: PostsApiService
    private lateinit var postsRepository: PostsRepository

    private val sampleDataApi =
        listOf(
            Post(
                1,
                1,
                "Description1",
                "period1"
            ),
            Post(
                2,
                2,
                "Description2",
                "period2"
            ),
            Post(
                3,
                3,
                "Description3",
                "period3"
            )
        )

    private val sampleDataInCache =
        listOf(
            Post(
                4,
                4,
                "Description4",
                "period4"
            ),
            Post(
                5,
                5,
                "Description5",
                "period5"
            )
        )

    @Before
    fun setup() {
        postsApiServiceMock = mockk(relaxed = true)
        postsRepository = PostsRepository(postsApiServiceMock)
    }

    @Test
    fun getPosts_when_emptycache_callapi_returnsApiData() {
        every { postsApiServiceMock.getPosts() }.returns(Observable.just(sampleDataApi))

        val postsReturned = postsRepository.getPosts()

        postsReturned.test().assertValue { it == sampleDataApi }
    }

    @Test
    fun getPosts_when_hasCache_returnsCache_thenApi() {
        every { postsApiServiceMock.getPosts() }.returns(Observable.just(sampleDataApi))

        postsRepository.cache = sampleDataInCache

        val postsReturned = postsRepository.getPosts()

        postsReturned.test().assertValueAt(0) { it == sampleDataInCache }
        postsReturned.test().assertValueAt(1) { it == sampleDataApi }
    }

    @Test
    fun getPosts_when_nocache_updatesCacheFromApi() {
        postsRepository.cache = listOf()

        every { postsApiServiceMock.getPosts() }.returns(Observable.just(sampleDataApi))

        val postsReturned = postsRepository.getPosts()

        postsReturned.test().assertValueAt(0) { it == sampleDataApi }

        assertEquals(postsRepository.cache, sampleDataApi)
    }

    @Test
    fun getPost_when_nocache_updatesCacheFromApi() {
        postsRepository.cache = listOf()

        val postId = 1
        val postItem = sampleDataApi.first { it.id == postId }

        every { postsApiServiceMock.getPost(postId) }.returns(Observable.just(postItem))

        val postsReturned = postsRepository.getPost(postId)

        postsReturned.test().assertValueAt(0) { it == postItem }

        assertTrue(postsRepository.cache.contains(postItem))

        verify(exactly = 1) { postsApiServiceMock.getPost(postId) }
    }

    @Test
    fun getPost_when_cached_getsFromCache() {
        postsRepository.cache = sampleDataInCache

        val postId = 4
        val postItem = sampleDataInCache.first { it.id == postId }

        val postsReturned = postsRepository.getPost(postId)

        postsReturned.test().assertValueAt(0) { it == postItem }

        verify(exactly = 0) { postsApiServiceMock.getPost(any()) }
    }
}

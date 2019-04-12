package com.postsapp.android.repository

import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.model.Post
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PostsRepositoryTest {
    private lateinit var postsApiServiceMock: PostsApiService
    private lateinit var postsRepository : PostsRepository

    @Before
    fun setup() {
        postsApiServiceMock = mockk(relaxed = true)
        postsRepository = PostsRepository(postsApiServiceMock)
    }

    @Test
    fun when_emptycache_callapi_returnsApiData() {
        every { postsApiServiceMock.getPosts() }.returns(Observable.just(sampleDataApi))

        val postsReturned = postsRepository.getPosts()

        postsReturned.test().assertValue { it == sampleDataApi }
    }

    @Test
    fun when_hasCache_returnsCache_thenApi() {
        every { postsApiServiceMock.getPosts() }.returns(Observable.just(sampleDataApi))

        postsRepository.cache = sampleDataInCache

        val postsReturned = postsRepository.getPosts()

        postsReturned.test().assertValueAt(0) { it == sampleDataInCache}
        postsReturned.test().assertValueAt(1) { it == sampleDataApi}
    }

    @Test
    fun when_nocache_updatesCacheFromApi() {
        postsRepository.cache = listOf()

        every { postsApiServiceMock.getPosts() }.returns(Observable.just(sampleDataApi))

        val postsReturned = postsRepository.getPosts()

        postsReturned.test().assertValueAt(0) { it == sampleDataApi}

        assertEquals(postsRepository.cache, sampleDataApi)
    }

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
}

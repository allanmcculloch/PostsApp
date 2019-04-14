package com.postsapp.android.repository

import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.model.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UsersRepositoryTest {
    private lateinit var postsApiServiceMock: PostsApiService
    private lateinit var usersRepository : UsersRepository

    @Before
    fun setup() {
        postsApiServiceMock = mockk(relaxed = true)
        usersRepository = UsersRepository(postsApiServiceMock)
    }

    @Test
    fun getUsers_when_emptycache_callapi_returnsApiData() {
        every { postsApiServiceMock.getUsers() }.returns(Observable.just(sampleDataApi))

        val usersReturned = usersRepository.getUsers()

        usersReturned.test().assertValue { it == sampleDataApi }
    }

    @Test
    fun getUsers_when_hasCache_returnsCache_thenApi() {
        every { postsApiServiceMock.getUsers() }.returns(Observable.just(sampleDataApi))

        usersRepository.cache = sampleDataInCache

        val usersReturned = usersRepository.getUsers()

        usersReturned.test().assertValueAt(0) { it == sampleDataInCache}
        usersReturned.test().assertValueAt(1) { it == sampleDataApi}
    }

    @Test
    fun getUsers_when_nocache_updatesCacheFromApi() {
        usersRepository.cache = listOf()

        every { postsApiServiceMock.getUsers() }.returns(Observable.just(sampleDataApi))

        val usersReturned = usersRepository.getUsers()

        usersReturned.test().assertValueAt(0) { it == sampleDataApi}

        assertEquals(usersRepository.cache, sampleDataApi)
    }


    @Test
    fun getUser_when_nocache_updatesCacheFromApi() {
        usersRepository.cache = listOf()

        val userId = 1
        val userItem = sampleDataApi.first { it.id == userId }

        every { postsApiServiceMock.getUser(userId) }.returns(Observable.just(userItem))

        val usersReturned = usersRepository.getUser(userId)

        usersReturned.test().assertValueAt(0) { it == userItem}

        Assert.assertTrue(usersRepository.cache.contains(userItem))

        verify(exactly = 1) { postsApiServiceMock.getUser(userId)}
    }

    @Test
    fun getUser_when_cached_getsFromCache() {
        usersRepository.cache = sampleDataInCache

        val userId = 3
        val userItem = sampleDataInCache.first { it.id == userId }

        val usersReturned = usersRepository.getUser(userId)

        usersReturned.test().assertValueAt(0) { it == userItem }

        verify(exactly = 0) { postsApiServiceMock.getUser(any())}
    }

    private val sampleDataApi =
        listOf(
            User(1, "User1"),
            User(2, "User2")

        )

    private val sampleDataInCache =
        listOf(
            User(3,"User3")
        )
}

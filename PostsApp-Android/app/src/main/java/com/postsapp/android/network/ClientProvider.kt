package com.postsapp.android.network

import com.postsapp.android.BASE_URL
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ClientProvider {
    private val okHttpClient: OkHttpClient = makeHttpClient()
    val client: Retrofit = createClient()

    private fun createClient(): Retrofit {
        val moshiBuilder = Moshi.Builder()

        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(moshiBuilder.build())
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    private fun makeHttpClient() =
         OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
}
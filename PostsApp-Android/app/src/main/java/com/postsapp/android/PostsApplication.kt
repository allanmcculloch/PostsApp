package com.postsapp.android

import android.app.Application
import com.postsapp.android.injection.PostsAppModule
import org.koin.android.ext.android.startKoin

class PostsApplication : Application() {
    override fun onCreate(){
        super.onCreate()

        val modules = listOf(PostsAppModule)

        startKoin(this, modules)
    }
}
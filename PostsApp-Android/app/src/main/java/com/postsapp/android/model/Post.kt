package com.postsapp.android.model

data class Post(
    var userId: Int,
    var id: Int,
    var title: String? = null,
    var body: String? = null
)

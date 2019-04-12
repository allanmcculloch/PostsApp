package com.postsapp.android.model

data class PostDetail(
    var userId: Int,
    var id : Int,
    var title : String? = null,
    var body : String? = null,
    var numberOfComments: Int? = null,
    var userName: String? = null
)
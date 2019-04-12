package com.postsapp.android.model

data class User(
    var id : Int,
    var name : String?,
    var username : String? = null,
    var email : String? = null,
    var address : Address? = null,
    var phone : String? = null,
    var website : String? = null,
    var company: Company? = null
)

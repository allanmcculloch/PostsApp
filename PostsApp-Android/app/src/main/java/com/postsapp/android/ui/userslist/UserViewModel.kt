package com.postsapp.android.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.postsapp.android.AVATAR_BASE_URL
import com.postsapp.android.model.User

class UserViewModel {
    private val _userName : MutableLiveData<String> = MutableLiveData()
    private val _email : MutableLiveData<String> = MutableLiveData()
    private val _imageUrl : MutableLiveData<String> = MutableLiveData()

    val userName: LiveData<String> = _userName
    val email: LiveData<String> = _email
    val imageUrl: LiveData<String> = _imageUrl

    fun bind(user : User) {

        _userName.value = user.name
        _email.value = user.email
        _imageUrl.value = "$AVATAR_BASE_URL/${user.id}.png"
    }
}

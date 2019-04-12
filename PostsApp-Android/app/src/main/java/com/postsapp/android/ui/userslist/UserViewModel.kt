package com.postsapp.android.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.postsapp.android.model.User

class UserViewModel {
    private val _userName : MutableLiveData<String> = MutableLiveData()
    private val _imageUrl : MutableLiveData<String> = MutableLiveData()

    val userName: LiveData<String> = _userName
    val imageUrl: LiveData<String> = _imageUrl

    fun bind(user : User) {

        _userName.value = user.name
        _imageUrl.value = "https://api.adorable.io/avatars/285/${user.id}.png"
    }
}

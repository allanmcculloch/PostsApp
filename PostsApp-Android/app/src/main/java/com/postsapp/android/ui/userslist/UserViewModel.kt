package com.postsapp.android.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.postsapp.android.AVATAR_BASE_URL
import com.postsapp.android.model.User

class UserViewModel : ViewModel() {
    private val _userName: MutableLiveData<String> = MutableLiveData()
    private val _email: MutableLiveData<String> = MutableLiveData()
    private val _imageUrl: MutableLiveData<String> = MutableLiveData()
    private val _company: MutableLiveData<String> = MutableLiveData()

    val userName: LiveData<String> = _userName
    val email: LiveData<String> = _email
    val imageUrl: LiveData<String> = _imageUrl
    val company: LiveData<String> = _company

    fun bind(user: User) {
        _userName.value = user.name
        _email.value = user.email
        _imageUrl.value = "$AVATAR_BASE_URL/${user.id}.png"
        _company.value = user.company?.name
    }
}

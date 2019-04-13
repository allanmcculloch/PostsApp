package com.postsapp.android.ui.commentslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.postsapp.android.model.Comment

class CommentViewModel {
    private val _name : MutableLiveData<String> = MutableLiveData()
    private val _body : MutableLiveData<String> = MutableLiveData()
    private val _email : MutableLiveData<String> = MutableLiveData()
    private val _imageUrl : MutableLiveData<String> = MutableLiveData()

    val body : LiveData<String> = _body
    val name : LiveData<String> = _name
    val email : LiveData<String> = _email
    val imageUrl : LiveData<String> = _imageUrl

    fun bind(comment : Comment) {
        _name.value = comment.name
        _body.value = comment.body
        _email.value = comment.email
        _imageUrl.value = "https://api.adorable.io/avatars/285/${comment.name}.png"
    }
}
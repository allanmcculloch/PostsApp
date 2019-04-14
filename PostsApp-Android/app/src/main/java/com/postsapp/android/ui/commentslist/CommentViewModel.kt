package com.postsapp.android.ui.commentslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.postsapp.android.AVATAR_BASE_URL
import com.postsapp.android.model.Comment

class CommentViewModel : ViewModel() {
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
        _imageUrl.value = "$AVATAR_BASE_URL/${comment.name}.png"
    }
}
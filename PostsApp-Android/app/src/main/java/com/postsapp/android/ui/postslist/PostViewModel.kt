package com.postsapp.android.ui.postslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.postsapp.android.model.Post

class PostViewModel {
    private val _body : MutableLiveData<String> = MutableLiveData()
    private val _title : MutableLiveData<String> = MutableLiveData()
    private val _imageUrl : MutableLiveData<String> = MutableLiveData()

    val body : LiveData<String> = _body
    val title : LiveData<String> = _title
    val imageUrl : LiveData<String> = _imageUrl

    fun bind(post : Post) {
        _body.value = post.body
        _title.value = post.title
        _imageUrl.value = "https://api.adorable.io/avatars/285/${post.userId}.png"
    }
}

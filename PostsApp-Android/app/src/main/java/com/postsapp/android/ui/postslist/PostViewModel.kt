package com.postsapp.android.ui.postslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.postsapp.android.AVATAR_BASE_URL
import com.postsapp.android.model.Post

class PostViewModel : ViewModel() {
    private val _body: MutableLiveData<String> = MutableLiveData()
    private val _title: MutableLiveData<String> = MutableLiveData()
    private val _imageUrl: MutableLiveData<String> = MutableLiveData()

    val body: LiveData<String> = _body
    val title: LiveData<String> = _title
    val imageUrl: LiveData<String> = _imageUrl

    fun bind(post: Post) {
        _body.value = post.body
        _title.value = post.title
        _imageUrl.value = "$AVATAR_BASE_URL/${post.userId}.png"
    }
}

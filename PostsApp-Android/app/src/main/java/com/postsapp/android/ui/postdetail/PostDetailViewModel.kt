package com.postsapp.android.ui.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.postsapp.android.AVATAR_BASE_URL
import com.postsapp.android.usecases.GetPostDetailUseCase
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class PostDetailViewModel(private val getPostDetailUseCase: GetPostDetailUseCase) : ViewModel() {
    private val _title : MutableLiveData<String> = MutableLiveData()
    private val _body : MutableLiveData<String> = MutableLiveData()
    private val _authorName : MutableLiveData<String> = MutableLiveData()
    private val _numberOfComments : MutableLiveData<String> = MutableLiveData()
    private val _imageUrl : MutableLiveData<String> = MutableLiveData()

    val title : LiveData<String> = _title
    val body : LiveData<String> = _body
    val authorName : LiveData<String> = _authorName
    val numberOfComments : LiveData<String> = _numberOfComments
    val imageUrl : LiveData<String> = _imageUrl

    var postId : Int? = null

    private lateinit var subscription: Disposable

    fun loadData(postId : Int) {
        this.postId = postId

        subscription = getPostDetailUseCase.execute(postId)
            .subscribe({
                _title.value = it?.title
                _body.value = it?.body
                _numberOfComments.value = it?.numberOfComments.toString()
                _authorName.value = it?.userName
                _imageUrl.value = "$AVATAR_BASE_URL/${it?.userId}.png"

            },Throwable::printStackTrace)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

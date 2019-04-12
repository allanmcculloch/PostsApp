package com.postsapp.android.ui.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.postsapp.android.usecases.GetPostUseCase
import io.reactivex.disposables.Disposable

class PostDetailViewModel(private val getPostUseCase: GetPostUseCase) : ViewModel() {
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

    private lateinit var subscription: Disposable

    fun loadData(postId : Int) {
        subscription = getPostUseCase.execute(postId)
            .subscribe({
                _title.value = it?.title
                _body.value = it?.body

            },Throwable::printStackTrace)


        _authorName.value = "test"
        _numberOfComments.value = "1"
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

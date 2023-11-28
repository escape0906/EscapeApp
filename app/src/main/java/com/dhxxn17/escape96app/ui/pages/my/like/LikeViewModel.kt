package com.dhxxn17.escape96app.ui.pages.my.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dhxxn17.domain.usecase.MyUseCase
import com.dhxxn17.escape96app.data.Theme
import com.dhxxn17.escape96app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val myUseCase: MyUseCase
): BaseViewModel() {

    private val _likeList = MutableLiveData<List<Theme>>()
    val likeList: LiveData<List<Theme>>
        get() = _likeList

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _entireProgressVisible = MutableLiveData<Boolean>()
    val entireProgressVisible: LiveData<Boolean>
        get() = _entireProgressVisible

    init {
        getAllLikeList()
    }

    fun getAllLikeList() {
        _likeList.postValue(emptyList())
        _entireProgressVisible.postValue(true)

        viewModelScope.launch {
            val result = myUseCase.getAllLikes().map {
                Theme(
                    id = it.id,
                    title = it.title,
                    thumbnail = it.thumbnail
                )
            }
            if (result.isNotEmpty()) {
                _likeList.postValue(result)
            } else {
                _message.postValue("다시 시도해주세요..")
            }
        }
        _entireProgressVisible.postValue(false)
    }
}
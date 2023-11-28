package com.dhxxn17.escape96app.ui.pages.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.usecase.DetailUseCase
import com.dhxxn17.escape96app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhxxn17.escape96app.data.Theme
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase
) : BaseViewModel() {

    private val _theme = MutableLiveData<Theme>()
    val theme: LiveData<Theme>
        get() = _theme

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _isLiked = MutableLiveData<Boolean>()
    val isLiked: LiveData<Boolean>
        get() = _isLiked

    private val _isSuccessed = MutableLiveData<Boolean>()
    val isSuccessed: LiveData<Boolean>
        get() = _isSuccessed


    fun getThemeDetail(id: Int) {
        viewModelScope.launch {
            val response = detailUseCase.getThemeDetail(id)
            when (response) {
                is NetworkResponse.Success -> {
                    val data = Theme(
                        id = response.body.id,
                        title = response.body.title,
                        thumbnail = response.body.thumbnail,
                        genre = response.body.genre,
                        store = response.body.store,
                        playTime = response.body.playTime,
                        recommendedPeople = response.body.recommendedPeople,
                        maximumPeople = response.body.maximumPeople
                    )
                    _theme.postValue(data)
                }

                is NetworkResponse.ApiError -> {
                    _message.postValue(response.message)
                }

                is NetworkResponse.NetworkError -> {
                    _message.postValue("네트워크를 확인해주세요")
                }

                else -> {
                    _message.postValue("잘못된 검색어입니다.")
                }
            }
        }
    }

    fun getIsLikeNSuccess(id: Int) {
        viewModelScope.launch {
            val isLike = detailUseCase.getIsLike(id)
            val isSuccess = detailUseCase.getIsSuccess(id)

            _isLiked.postValue(isLike)
            _isSuccessed.postValue(isSuccess)
        }
    }

    fun addLikeTheme() {
        viewModelScope.launch {
             _theme.value?.let {
                val isDone = detailUseCase.addLike(
                    com.dhxxn17.domain.model.Theme(
                        id = it.id,
                        title = it.title,
                        thumbnail = it.thumbnail
                    )
                )

                if (isDone > 0) {
                    _isLiked.postValue(true)
                    _message.postValue("찜에 추가하였습니다.")
                } else {
                    _message.postValue("실패하였습니다. 잠시 후 다시 시도해주세요!")
                }
            }

        }
    }

    fun deleteLikeTheme() {
        viewModelScope.launch {
            _theme.value?.let {
                val isDone = detailUseCase.deleteLike(
                    it.id
                )

                if (isDone > 0) {
                    _isLiked.postValue(false)
                    _message.postValue("찜에서 삭제하였습니다.")
                } else {
                    _message.postValue("실패하였습니다. 잠시 후 다시 시도해주세요!")
                }
            }
        }
    }

    fun addSuccessTheme() {
        viewModelScope.launch {
            _theme.value?.let {
                val isDone = detailUseCase.addSuccess(
                    com.dhxxn17.domain.model.Theme(
                        id = it.id,
                        title = it.title,
                        thumbnail = it.thumbnail
                    )
                )

                if (isDone > 0) {
                    _isSuccessed.postValue(true)
                    _message.postValue("완료에 추가하였습니다.")
                } else {
                    _message.postValue("실패하였습니다. 잠시 후 다시 시도해주세요!")
                }
            }

        }
    }

    fun deleteSuccessTheme() {
        viewModelScope.launch {
            _theme.value?.let {
                val isDone = detailUseCase.deleteSuccess(
                    it.id
                )

                if (isDone > 0) {
                    _isSuccessed.postValue(false)
                    _message.postValue("완료에서 삭제하였습니다.")
                } else {
                    _message.postValue("실패하였습니다. 잠시 후 다시 시도해주세요!")
                }
            }
        }
    }
}
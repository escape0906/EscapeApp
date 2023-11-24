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
    val errorMessage: LiveData<String>
        get() = _message


    fun getThemeDetail(id: Int) {
        viewModelScope.launch {
            val response = detailUseCase.getThemeDetail(id)
            when(response) {
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
}
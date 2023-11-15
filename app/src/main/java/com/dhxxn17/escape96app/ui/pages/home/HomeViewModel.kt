package com.dhxxn17.escape96app.ui.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.usecase.HomeUseCase
import com.dhxxn17.escape96app.data.Theme
import com.dhxxn17.escape96app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private val _themeList = MutableLiveData<List<Theme>>()
    val themeList: LiveData<List<Theme>>
        get() = _themeList

    private val _message = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _message

    init {
        getAllThemeList()
    }

    fun getAllThemeList() {
        viewModelScope.launch {
            val response = homeUseCase.getAllThemes()
            when(response) {
                is NetworkResponse.Success -> {
                    val list = response.body.map {
                        Theme(
                            id = it.id,
                            title = it.title,
                            location = it.location,
                            thumbnail = it.thumbnail
                        )
                    }
                    _themeList.postValue(list)
                }
                is NetworkResponse.ApiError -> {
                    _message.postValue(response.message)
                }
                is NetworkResponse.NetworkError -> {
                    _message.postValue("네트워크를 확인해주세요")
                }
                is NetworkResponse.UnknownError -> {
                    _message.postValue("잘못된 검색어입니다.")
                }
            }
        }
    }
}
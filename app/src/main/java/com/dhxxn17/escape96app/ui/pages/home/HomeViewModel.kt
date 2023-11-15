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

    private val PAGE_SIZE = 20
    private var page = 0
    private var isEnd = false

    private val _themeList = MutableLiveData<List<Theme>>()
    val themeList: LiveData<List<Theme>>
        get() = _themeList

    private val _message = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _message

    private val _entireProgressVisible = MutableLiveData<Boolean>()
    val entireProgressVisible: LiveData<Boolean>
        get() = _entireProgressVisible

    init {
        getAllThemeList()
    }

    fun getAllThemeList() {
        _themeList.postValue(emptyList())
        page = 0
        _entireProgressVisible.postValue(true)

        viewModelScope.launch {
            val response = homeUseCase.getAllThemes(
                page = page,
                size = PAGE_SIZE
            )
            when (response) {
                is NetworkResponse.Success -> {
                    val list = response.body.content.map {
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

            _entireProgressVisible.postValue(false)
            isEnd = false
        }
    }

    fun loadMore() {
        if (!isEnd) {
            _entireProgressVisible.postValue(true)
            val currentList = _themeList.value
            viewModelScope.launch {
                page++
                val response = homeUseCase.getAllThemes(page, PAGE_SIZE)
                when (response) {
                    is NetworkResponse.Success -> {
                        isEnd = response.body.isLast
                        val responseData = response.body.content.map {
                            Theme(
                                id = it.id,
                                title = it.title,
                                location = it.location,
                                thumbnail = it.thumbnail
                            )
                        }
                        currentList?.toMutableList()
                            ?.apply { addAll(responseData) }?.let {
                                _themeList.postValue(it)
                            }

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
                _entireProgressVisible.postValue(false)
            }
        }
    }
}
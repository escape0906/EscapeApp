package com.dhxxn17.escape96app.ui.pages.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.usecase.SearchUseCase
import com.dhxxn17.escape96app.data.Theme
import com.dhxxn17.escape96app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): BaseViewModel() {

    private val _resultList = MutableLiveData<List<Theme>>()
    val resultList: LiveData<List<Theme>>
        get() = _resultList

    private val _entireProgressVisible = MutableLiveData<Boolean>()
    val entireProgressVisible: LiveData<Boolean>
        get() = _entireProgressVisible

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val _totalCount = MutableLiveData<Int>()
    val totalCount: LiveData<Int>
        get() = _totalCount

    private val _message = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _message

    private val PAGE_SIZE = 20
    private var page = 0
    private var isEnd = false

    fun requestSearchTheme(input: String) {
        _searchQuery.postValue(input)
        _resultList.postValue(emptyList())
        page = 0
        _entireProgressVisible.postValue(true)

        viewModelScope.launch {
            val response = searchUseCase.requestSearchTheme(
                input = input,
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
                    _totalCount.postValue(response.body.totalElements)
                    isEnd = response.body.isLast
                    _resultList.postValue(list)
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
            val currentList = _resultList.value
            viewModelScope.launch {
                page++
                val response = searchUseCase.requestSearchStore(
                    _searchQuery.value ?: "",
                    page,
                    PAGE_SIZE
                )

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
                            ?.apply {
                                addAll(responseData)
                            }?.let {
                                _resultList.postValue(it)
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
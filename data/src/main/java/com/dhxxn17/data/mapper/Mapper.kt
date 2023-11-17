package com.dhxxn17.data.mapper

import com.dhxxn17.data.model.ResponseSearch
import com.dhxxn17.data.model.ResponseTheme
import com.dhxxn17.data.model.ResponseThemes
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseSearchList
import com.dhxxn17.domain.model.ResponseThemeList
import com.dhxxn17.domain.model.Theme
import retrofit2.Response

fun listMapper(
    response: Response<ResponseThemes>
): NetworkResponse<ResponseThemeList> {
    if (response.isSuccessful) {
        response.body()?.let{ _response ->
            return NetworkResponse.Success(
                body = ResponseThemeList(
                    isLast = _response.isLast,
                    content = _response.content.map {
                        Theme(
                            id = it.id,
                            title = it.title,
                            location = it.location,
                            thumbnail = it.thumbnail
                        )
                    }
                )
            )
        }
    }
    return NetworkResponse.ApiError(
        message = response.message(),
        code = response.code()
    )
}

fun searchListMapper(
    response: Response<ResponseSearch>
): NetworkResponse<ResponseSearchList> {
    if (response.isSuccessful) {
        response.body()?.let{ _response ->
            return NetworkResponse.Success(
                body = ResponseSearchList(
                    totalElements = _response.totalCount,
                    isLast = _response.isLast,
                    content = _response.content.map {
                        Theme(
                            id = it.id,
                            title = it.title,
                            location = it.location,
                            thumbnail = it.thumbnail
                        )
                    }
                )
            )
        }
    }
    return NetworkResponse.ApiError(
        message = response.message(),
        code = response.code()
    )
}

fun themeMapper(
    response: Response<ResponseTheme>
): NetworkResponse<Theme> {
    if(response.isSuccessful) {
        response.body()?.let {
            return NetworkResponse.Success(
                body = Theme(
                    id = it.id,
                    title = it.title,
                    thumbnail = it.thumbnail,
                    genre = it.genre,
                    store = it.store,
                    playTime = it.playTime,
                    recommendedPeople = it.recommendedPeople,
                    maximumPeople = it.maximumPeople
                )
            )
        }
    }
    return NetworkResponse.ApiError(
        message = response.message(),
        code = response.code()
    )
}
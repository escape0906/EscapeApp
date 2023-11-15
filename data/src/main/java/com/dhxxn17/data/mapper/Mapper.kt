package com.dhxxn17.data.mapper

import com.dhxxn17.data.model.ResponseThemes
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import retrofit2.Response

fun listMapper(
    response: Response<ResponseThemes>
): NetworkResponse<List<Theme>> {
    if (response.isSuccessful) {
        response.body()?.let{ _response ->
            return NetworkResponse.Success(
                body = _response.content.map {
                    Theme(
                        id = it.id,
                        title = it.title,
                        location = it.location,
                        thumbnail = it.thumbnail
                    )
                }
            )
        }
    }
    return NetworkResponse.ApiError(
        message = response.message(),
        code = response.code()
    )
}
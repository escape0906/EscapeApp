package com.dhxxn17.data.datasource

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseSearchList

interface SearchDataSource {
    suspend fun requestSearchStore(
        input: String,
        page: Int,
        size: Int
    ): NetworkResponse<ResponseSearchList>

    suspend fun requestSearchTheme(
        input: String,
        page: Int,
        size: Int,
        difficult: String?,
        address: String?
    ): NetworkResponse<ResponseSearchList>
}
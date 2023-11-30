package com.dhxxn17.domain.repository

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseSearchList

interface SearchRepository {

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
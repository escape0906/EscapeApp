package com.dhxxn17.data.datasource

import com.dhxxn17.data.NetworkApiService
import com.dhxxn17.data.mapper.searchListMapper
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseSearchList
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService
): SearchDataSource {
    override suspend fun requestSearchStore(
        input: String,
        page: Int,
        size: Int
    ): NetworkResponse<ResponseSearchList> {
        return searchListMapper(
            apiService.requestSearchStore(store = input, page = page, size = size)
        )
    }

    override suspend fun requestSearchTheme(
        input: String,
        page: Int,
        size: Int,
        difficult: String?,
        address: String?
    ): NetworkResponse<ResponseSearchList> {
        return searchListMapper(
            apiService.requestSearchTheme(keyword = input, page = page, size = size, difficult = difficult, address = address)
        )
    }
}
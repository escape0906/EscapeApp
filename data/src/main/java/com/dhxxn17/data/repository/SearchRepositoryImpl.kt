package com.dhxxn17.data.repository

import com.dhxxn17.data.datasource.SearchDataSource
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseSearchList
import com.dhxxn17.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
): SearchRepository {
    override suspend fun requestSearchStore(
        input: String,
        page: Int,
        size: Int
    ): NetworkResponse<ResponseSearchList> {
        return searchDataSource.requestSearchStore(input, page, size)
    }

    override suspend fun requestSearchTheme(
        input: String,
        page: Int,
        size: Int,
        difficult: String?,
        address: String?
    ): NetworkResponse<ResponseSearchList> {
        return searchDataSource.requestSearchTheme(input, page, size,  difficult, address)
    }
}
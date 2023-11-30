package com.dhxxn17.domain.usecase

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseSearchList
import com.dhxxn17.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend fun requestSearchStore(
        input: String,
        page: Int,
        size: Int
    ): NetworkResponse<ResponseSearchList> {
        return searchRepository.requestSearchStore(input, page, size)
    }

    suspend fun requestSearchTheme(
        input: String,
        page: Int,
        size: Int,
        difficult: String?,
        address: String?
    ): NetworkResponse<ResponseSearchList> {
        return searchRepository.requestSearchTheme(input, page, size, difficult, address)
    }
}
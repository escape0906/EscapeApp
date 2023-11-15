package com.dhxxn17.data.repository

import com.dhxxn17.data.datasource.DetailThemeDataSource
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import com.dhxxn17.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailThemeDataSource: DetailThemeDataSource
): DetailRepository {
    override suspend fun getThemeDetail(id: Int): NetworkResponse<Theme> {
        return detailThemeDataSource.getThemeDetail(id)
    }
}
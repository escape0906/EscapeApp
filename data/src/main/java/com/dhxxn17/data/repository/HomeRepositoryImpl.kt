package com.dhxxn17.data.repository

import com.dhxxn17.data.datasource.HomeThemeDataSource
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseThemeList
import com.dhxxn17.domain.model.Theme
import com.dhxxn17.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeThemeDataSource: HomeThemeDataSource
): HomeRepository {
    override suspend fun getAllThemes(page: Int, size: Int): NetworkResponse<ResponseThemeList> {
        return homeThemeDataSource.getAllThemes(page, size)
    }
}
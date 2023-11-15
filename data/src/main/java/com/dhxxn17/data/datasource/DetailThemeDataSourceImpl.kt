package com.dhxxn17.data.datasource

import com.dhxxn17.data.NetworkApiService
import com.dhxxn17.data.mapper.themeMapper
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import javax.inject.Inject

class DetailThemeDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService
) : DetailThemeDataSource {
    override suspend fun getThemeDetail(id: Int): NetworkResponse<Theme> {
        return themeMapper(apiService.getThemeDetail(id))
    }

}
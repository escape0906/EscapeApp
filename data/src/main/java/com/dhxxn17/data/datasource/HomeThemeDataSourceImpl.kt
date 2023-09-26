package com.dhxxn17.data.datasource

import com.dhxxn17.data.NetworkApiService
import com.dhxxn17.data.mapper.listMapper
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import javax.inject.Inject

class HomeThemeDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService
) : HomeThemeDataSource {

    override suspend fun getAllThemes(): NetworkResponse<List<Theme>> {
        return listMapper(
            apiService.getAllThemes()
        )
    }

}
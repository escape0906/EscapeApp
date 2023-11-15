package com.dhxxn17.data.datasource

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseThemeList

interface HomeThemeDataSource {
    suspend fun getAllThemes(page: Int, size: Int): NetworkResponse<ResponseThemeList>
}
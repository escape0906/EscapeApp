package com.dhxxn17.data.datasource

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme

interface HomeThemeDataSource {
    suspend fun getAllThemes(): NetworkResponse<List<Theme>>
}
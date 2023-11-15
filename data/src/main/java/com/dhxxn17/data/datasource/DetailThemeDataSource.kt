package com.dhxxn17.data.datasource

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme

interface DetailThemeDataSource {
    suspend fun getThemeDetail(id: Int): NetworkResponse<Theme>
}
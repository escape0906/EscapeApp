package com.dhxxn17.domain.repository

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseThemeList

interface HomeRepository {
    suspend fun getAllThemes(page: Int, size: Int): NetworkResponse<ResponseThemeList>
}
package com.dhxxn17.domain.repository

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme

interface DetailRepository {
    suspend fun getThemeDetail(id: Int): NetworkResponse<Theme>
}
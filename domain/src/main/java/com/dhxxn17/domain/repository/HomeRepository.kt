package com.dhxxn17.domain.repository

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme

interface HomeRepository {
    suspend fun getAllThemes(): NetworkResponse<List<Theme>>
}
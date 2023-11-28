package com.dhxxn17.domain.repository

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme

interface DetailRepository {
    suspend fun getThemeDetail(id: Int): NetworkResponse<Theme>

    suspend fun getIsLike(id: Int): Boolean

    suspend fun getIsSuccess(id: Int): Boolean

    suspend fun addLike(data: Theme): Long

    suspend fun addSuccess(data: Theme): Long

    suspend fun deleteLike(themeId: Int): Int

    suspend fun deleteSuccess(themeId: Int): Int
}
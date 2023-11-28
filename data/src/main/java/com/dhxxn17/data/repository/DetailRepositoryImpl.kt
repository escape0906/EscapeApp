package com.dhxxn17.data.repository

import com.dhxxn17.data.datasource.DetailThemeDataSource
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import com.dhxxn17.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailThemeDataSource: DetailThemeDataSource
): DetailRepository {
    override suspend fun getThemeDetail(id: Int): NetworkResponse<Theme> {
        return detailThemeDataSource.getThemeDetail(id)
    }

    override suspend fun getIsLike(id: Int): Boolean {
        return detailThemeDataSource.getIsLike(id)
    }

    override suspend fun getIsSuccess(id: Int): Boolean {
        return detailThemeDataSource.getIsSuccess(id)
    }

    override suspend fun addLike(data: Theme): Long {
        return detailThemeDataSource.addLike(data)
    }

    override suspend fun addSuccess(data: Theme): Long {
        return detailThemeDataSource.addSuccess(data)
    }

    override suspend fun deleteLike(themeId: Int): Int {
        return detailThemeDataSource.deleteLike(themeId)
    }

    override suspend fun deleteSuccess(themeId: Int): Int {
        return detailThemeDataSource.deleteSuccess(themeId)
    }
}
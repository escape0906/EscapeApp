package com.dhxxn17.data.datasource

import com.dhxxn17.data.NetworkApiService
import com.dhxxn17.data.local.LikeDao
import com.dhxxn17.data.local.SuccessDao
import com.dhxxn17.data.mapper.themeMapper
import com.dhxxn17.data.model.LikeData
import com.dhxxn17.data.model.SuccessData
import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import javax.inject.Inject

class DetailThemeDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService,
    private val likeDao: LikeDao,
    private val successDao: SuccessDao
) : DetailThemeDataSource {
    override suspend fun getThemeDetail(id: Int): NetworkResponse<Theme> {
        return themeMapper(apiService.getThemeDetail(id))
    }

    override suspend fun getIsLike(id: Int): Boolean {
        return likeDao.getIsLike(id)
    }

    override suspend fun getIsSuccess(id: Int): Boolean {
        return successDao.getIsSuccess(id)
    }

    override suspend fun addLike(data: Theme): Long {
        return likeDao.addLike(
            LikeData(
                themeId = data.id,
                title = data.title,
                thumbnailImg = data.thumbnail
            )
        )
    }

    override suspend fun addSuccess(data: Theme): Long {
        return successDao.addSuccess(
            SuccessData(
                themeId = data.id,
                title = data.title,
                thumbnailImg = data.thumbnail
            )
        )
    }

    override suspend fun deleteLike(themeId: Int): Int {
        return likeDao.deleteLike(
            themeId
        )
    }

    override suspend fun deleteSuccess(themeId: Int): Int {
        return successDao.deleteSuccess(
            themeId
        )
    }

}
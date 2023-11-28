package com.dhxxn17.data.datasource

import com.dhxxn17.data.local.LikeDao
import com.dhxxn17.data.local.SuccessDao
import com.dhxxn17.data.mapper.likeMapper
import com.dhxxn17.data.mapper.successMapper
import com.dhxxn17.domain.model.Theme
import javax.inject.Inject

class MyDataSourceImpl @Inject constructor(
    private val likeDao: LikeDao,
    private val successDao: SuccessDao
): MyDataSource {
    override suspend fun getAllLikes(): List<Theme> {
        return likeMapper(likeDao.getAllLikes())
    }

    override suspend fun getAllSuccess(): List<Theme> {
        return successMapper(successDao.getAllLSuccesses())
    }
}
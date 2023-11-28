package com.dhxxn17.data.repository

import com.dhxxn17.data.datasource.MyDataSource
import com.dhxxn17.domain.model.Theme
import com.dhxxn17.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myDataSource: MyDataSource
): MyRepository {
    override suspend fun getAllLikes(): List<Theme> {
        return myDataSource.getAllLikes()
    }

    override suspend fun getAllSuccess(): List<Theme> {
        return myDataSource.getAllSuccess()
    }
}
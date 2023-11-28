package com.dhxxn17.data.datasource

import com.dhxxn17.domain.model.Theme

interface MyDataSource {

    suspend fun getAllLikes(): List<Theme>

    suspend fun getAllSuccess(): List<Theme>
}
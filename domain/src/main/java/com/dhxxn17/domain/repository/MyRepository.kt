package com.dhxxn17.domain.repository

import com.dhxxn17.domain.model.Theme

interface MyRepository {

    suspend fun getAllLikes(): List<Theme>

    suspend fun getAllSuccess(): List<Theme>
}
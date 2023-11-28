package com.dhxxn17.domain.usecase

import com.dhxxn17.domain.model.Theme
import com.dhxxn17.domain.repository.MyRepository
import javax.inject.Inject

class MyUseCase @Inject constructor(
    private val myRepository: MyRepository
) {

    suspend fun getAllLikes(): List<Theme> {
        return myRepository.getAllLikes()
    }

    suspend fun getAllSuccess(): List<Theme> {
        return myRepository.getAllSuccess()
    }
}
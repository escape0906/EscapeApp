package com.dhxxn17.domain.usecase

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import com.dhxxn17.domain.repository.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getAllThemes(): NetworkResponse<List<Theme>> {
        return homeRepository.getAllThemes()
    }
}
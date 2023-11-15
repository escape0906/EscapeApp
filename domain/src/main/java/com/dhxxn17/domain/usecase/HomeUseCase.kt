package com.dhxxn17.domain.usecase

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.ResponseThemeList
import com.dhxxn17.domain.repository.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getAllThemes(page: Int, size: Int): NetworkResponse<ResponseThemeList> {
        return homeRepository.getAllThemes(page, size)
    }
}
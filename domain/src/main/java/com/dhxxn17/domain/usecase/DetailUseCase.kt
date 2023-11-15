package com.dhxxn17.domain.usecase

import com.dhxxn17.domain.NetworkResponse
import com.dhxxn17.domain.model.Theme
import com.dhxxn17.domain.repository.DetailRepository
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    suspend fun getThemeDetail(id: Int): NetworkResponse<Theme> {
        return detailRepository.getThemeDetail(id)
    }
}
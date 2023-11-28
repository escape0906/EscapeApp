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

    // 찜 했는지 여부 알아오기
    suspend fun getIsLike(id: Int): Boolean {
        return detailRepository.getIsLike(id)
    }

    // 완료 했는지 여부 알아오기
    suspend fun getIsSuccess(id: Int): Boolean {
        return detailRepository.getIsSuccess(id)
    }

    // 찜 추가
    suspend fun addLike(data: Theme): Long {
        return detailRepository.addLike(data)
    }

    // 완료 추가
    suspend fun addSuccess(data: Theme): Long {
        return detailRepository.addSuccess(data)
    }

    // 찜 삭제
    suspend fun deleteLike(themeId: Int): Int {
        return detailRepository.deleteLike(themeId)
    }

    // 완료 삭제
    suspend fun deleteSuccess(themeId: Int): Int {
        return detailRepository.deleteSuccess(themeId)
    }

}
package com.dhxxn17.data

import com.dhxxn17.data.model.ResponseTheme
import com.dhxxn17.data.model.ResponseThemes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApiService {
    // 전체 테마 조회
    @GET("/api/themes")
    suspend fun getAllThemes(): Response<ResponseThemes>

    // 개별 테마 조회
    @GET("/api/themes/{themeId}")
    suspend fun getThemeDetail(@Path("themeId") themeId: Int): Response<ResponseTheme>
}
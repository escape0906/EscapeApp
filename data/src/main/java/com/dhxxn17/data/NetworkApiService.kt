package com.dhxxn17.data

import com.dhxxn17.data.model.ResponseSearch
import com.dhxxn17.data.model.ResponseTheme
import com.dhxxn17.data.model.ResponseThemes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiService {
    // 전체 테마 조회
    @GET("/api/themes")
    suspend fun getAllThemes(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseThemes>

    // 개별 테마 조회
    @GET("/api/themes/{themeId}")
    suspend fun getThemeDetail(@Path("themeId") themeId: Int): Response<ResponseTheme>

    // 매장별 검색
    @GET("/api/themes")
    suspend fun requestSearchStore(
        @Query("type") type: String = "store",
        @Query("store") store: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseSearch>
}
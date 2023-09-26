package com.dhxxn17.data

import com.dhxxn17.data.model.ResponseThemes
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {
    @GET("")
    suspend fun getAllThemes(): Response<ResponseThemes>
}
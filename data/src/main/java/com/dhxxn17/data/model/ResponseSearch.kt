package com.dhxxn17.data.model


import com.dhxxn17.domain.model.Theme
import com.google.gson.annotations.SerializedName

data class ResponseSearch(
    @SerializedName("totalElements")
    val totalCount: Int,
    @SerializedName("last")
    val isLast: Boolean,
    @SerializedName("content")
    val content: List<Theme>
)

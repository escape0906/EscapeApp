package com.dhxxn17.data.model

import com.google.gson.annotations.SerializedName

data class ResponseThemes(
    @SerializedName("last")
    val isLast: Boolean,
    @SerializedName("content")
    val content: List<Themes>
)

data class Themes(
    val id: Int,
    val title: String,
    val location: String,
    val thumbnail: String
)
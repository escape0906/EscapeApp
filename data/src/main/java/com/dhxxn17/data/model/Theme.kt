package com.dhxxn17.data.model

data class ResponseTheme(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val genre: String,
    val store: String,
    val playTime: Int,
    val recommendedPeople: String,
    val maximumPeople: Int
)

package com.dhxxn17.data.model

data class ResponseThemes(
    val content: List<Themes>
)

data class Themes(
    val title: String,
    val location: String,
    val thumbnail: String
)
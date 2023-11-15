package com.dhxxn17.domain.model

data class Theme(
    val id: Int = 0,
    val title: String = "",
    val thumbnail: String = "",
    val location: String = "",
    val genre: String = "",
    val store: String = "",
    val playTime: Int = 0,
    val recommendedPeople: String = "",
    val maximumPeople: Int = 0
)

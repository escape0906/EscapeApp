package com.dhxxn17.domain.model

data class ResponseSearchList(
    val totalElements: Int,
    val isLast: Boolean,
    val content: List<Theme>
)

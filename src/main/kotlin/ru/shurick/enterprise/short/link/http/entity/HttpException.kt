package ru.shurick.enterprise.short.link.http.entity

data class HttpException(
    val timestamp: String,
    val code: Int,
    val error: String,
    val message: String?
)
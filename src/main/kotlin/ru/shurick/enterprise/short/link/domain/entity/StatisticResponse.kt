package ru.shurick.enterprise.short.link.domain.entity

data class StatisticResponse(
    val link: String,
    val original: String,
    val rank: Int,
    val count: Int
)
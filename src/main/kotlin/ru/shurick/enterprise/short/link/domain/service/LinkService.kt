package ru.shurick.enterprise.short.link.domain.service

import ru.shurick.enterprise.short.link.domain.entity.LinkRequest
import ru.shurick.enterprise.short.link.domain.entity.LinkResponse
import ru.shurick.enterprise.short.link.domain.entity.StatisticResponse

interface LinkService {

    fun save(link: LinkRequest): LinkResponse
    fun load(linkId: String): String

    fun statistics(linkId: String): StatisticResponse
    fun statistics(page: Int, count: Int): List<StatisticResponse>
}
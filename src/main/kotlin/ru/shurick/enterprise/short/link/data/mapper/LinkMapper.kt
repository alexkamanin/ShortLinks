package ru.shurick.enterprise.short.link.data.mapper

import ru.shurick.enterprise.short.link.data.dto.LinkDto
import ru.shurick.enterprise.short.link.domain.entity.LinkResponse
import ru.shurick.enterprise.short.link.domain.entity.StatisticResponse

fun LinkDto.toLinkResponse() = LinkResponse(link)

fun LinkDto.toStatisticResponse() = StatisticResponse(link, original, rank, count)
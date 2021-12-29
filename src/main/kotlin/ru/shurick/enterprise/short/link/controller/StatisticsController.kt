package ru.shurick.enterprise.short.link.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.shurick.enterprise.short.link.domain.entity.StatisticResponse
import ru.shurick.enterprise.short.link.domain.service.LinkService
import ru.shurick.enterprise.short.link.http.base.handler.HandlerExceptionResolver

@RestController
@RequestMapping("/statistics")
@Api(tags = ["Statistics"])
@Tag(name = "Statistics", description = "")
class StatisticsController(
    private val service: LinkService,
    @Qualifier("statisticException") private val handlerExceptionResolver: HandlerExceptionResolver
) : HandlerExceptionResolver by handlerExceptionResolver {

    private companion object Paging {
        const val defaultPage = "1"
        const val defaultCount = "10"
    }

    @GetMapping("/{linkId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation("Getting statistics of visits to a specific site")
    fun statistics(@PathVariable linkId: String): StatisticResponse =
        service.statistics(linkId)

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation("Getting a list of site visit statistics")
    fun statistics(
        @RequestParam(required = false, defaultValue = defaultPage) page: Int,
        @RequestParam(required = false, defaultValue = defaultCount) count: Int
    ): List<StatisticResponse> =
        service.statistics(page, count)
}
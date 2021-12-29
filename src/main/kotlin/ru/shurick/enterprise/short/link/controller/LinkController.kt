package ru.shurick.enterprise.short.link.controller

import io.swagger.annotations.Api
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.shurick.enterprise.short.link.domain.entity.LinkRequest
import ru.shurick.enterprise.short.link.domain.entity.LinkResponse
import ru.shurick.enterprise.short.link.domain.service.LinkService
import ru.shurick.enterprise.short.link.http.base.handler.HandlerExceptionResolver
import java.net.URI

@RestController
@RequestMapping("/links")
@Api(tags = ["Links"])
@Tag(name = "Links", description = "")
class LinkController(private val service: LinkService) {

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun generate(@RequestBody link: LinkRequest): LinkResponse =
        service.save(link)

    @GetMapping("/{linkId}")
    fun redirect(@PathVariable linkId: String): ResponseEntity<URI> =
        ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(service.load(linkId)))
            .build()
}
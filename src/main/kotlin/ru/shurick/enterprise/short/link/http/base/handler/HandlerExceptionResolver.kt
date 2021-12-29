package ru.shurick.enterprise.short.link.http.base.handler

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.shurick.enterprise.short.link.http.entity.HttpException

interface HandlerExceptionResolver {

    @ExceptionHandler(value = [Exception::class])
    fun handleException(exception: Exception): ResponseEntity<HttpException>
}
package ru.shurick.enterprise.short.link.http.handler

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import ru.shurick.enterprise.short.link.base.exception.BaseException
import ru.shurick.enterprise.short.link.http.base.handler.HandlerExceptionResolver
import ru.shurick.enterprise.short.link.http.builder.httpResponse
import ru.shurick.enterprise.short.link.http.entity.HttpException

@Component
@Qualifier("statisticException")
class StatisticHandlerExceptionResolver : HandlerExceptionResolver {

    override fun handleException(exception: Exception): ResponseEntity<HttpException> =
        when (exception) {

            is BaseException.NoSuchElementException -> httpResponse {
                status = HttpStatus.NOT_FOUND
                message = exception.message
            }

            else -> httpResponse()
        }
}
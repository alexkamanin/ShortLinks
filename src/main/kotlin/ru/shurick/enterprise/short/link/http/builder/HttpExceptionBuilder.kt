package ru.shurick.enterprise.short.link.http.builder

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import ru.shurick.enterprise.short.link.http.entity.HttpException
import ru.shurick.enterprise.short.link.base.time.NowDateTimeFormat

class HttpExceptionBuilder {

    var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    var message: String? = null

    fun build(): ResponseEntity<HttpException> =
        ResponseEntity
            .status(status)
            .body(
                HttpException(
                    timestamp = NowDateTimeFormat(),
                    code = status.value(),
                    error = status.reasonPhrase,
                    message = message
                )
            )
}

fun httpResponse(builder: HttpExceptionBuilder.() -> Unit): ResponseEntity<HttpException> =
    HttpExceptionBuilder().apply(builder).build()

fun httpResponse(): ResponseEntity<HttpException> =
    HttpExceptionBuilder().build()
package ru.shurick.enterprise.short.link.base.time

import java.text.SimpleDateFormat
import java.util.*

object NowDateTimeFormat : SimpleDateFormat("dd.MM.yyyy HH:mm:ss") {

    operator fun invoke(): String = format(Date())
}
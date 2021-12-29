package ru.shurick.enterprise.short.link.base.exception

/**
 * Base class of all exceptions, which throws server
 *
 * @param linkId standard message exception
 */

sealed class BaseException(override val message: String?): Exception() {

    class NoSuchElementException(linkId: String): BaseException("Not found original by link = $linkId")
}
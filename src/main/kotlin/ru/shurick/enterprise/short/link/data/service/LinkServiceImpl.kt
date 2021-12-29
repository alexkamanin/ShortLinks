package ru.shurick.enterprise.short.link.data.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import ru.shurick.enterprise.short.link.data.dao.LinkDao
import ru.shurick.enterprise.short.link.data.dto.LinkDto
import ru.shurick.enterprise.short.link.data.mapper.toLinkResponse
import ru.shurick.enterprise.short.link.data.mapper.toStatisticResponse
import ru.shurick.enterprise.short.link.domain.entity.LinkRequest
import ru.shurick.enterprise.short.link.domain.entity.LinkResponse
import ru.shurick.enterprise.short.link.domain.entity.StatisticResponse
import ru.shurick.enterprise.short.link.domain.service.LinkService
import ru.shurick.enterprise.short.link.base.exception.BaseException

@Service
class LinkServiceImpl(private val dao: LinkDao) : LinkService {

    private companion object {

        const val FIRST_PAGE_NUMBER = 1
        const val SORT_BY_PROPERTY = "count"
    }

    override fun save(link: LinkRequest): LinkResponse {
        return with(dao) {
            val dto = findByOriginal(link.original) ?: run {
                save(
                    LinkDto(
                        original = link.original,
                        link = link.original.toShortURL()
                    )
                )
            }
            dto.toLinkResponse()
        }
    }

    override fun load(linkId: String): String {
        return dao.findByLink(linkId)?.original.also { dao.increaseSessionCounter(linkId) }
            ?: throw BaseException.NoSuchElementException(linkId)
    }

    override fun statistics(linkId: String): StatisticResponse {
        updateStatistic()
        return dao.findByLink(linkId)?.toStatisticResponse()
            ?: throw BaseException.NoSuchElementException(linkId)
    }

    override fun statistics(page: Int, count: Int): List<StatisticResponse> {

        updateStatistic()

        return dao.findAll(
            PageRequest.of(
                page startWithPage FIRST_PAGE_NUMBER,
                count,
                Sort.by(Sort.Order.desc(SORT_BY_PROPERTY))
            )
        ).map { it.toStatisticResponse() }.toList()
    }

    private infix fun Int.startWithPage(page: Int) =
        if (this > 0) {
            this - page
        } else {
            0
        }

    private fun String.toShortURL() =
        Integer.toHexString(this.hashCode())

    private fun updateStatistic() {
        with(dao) {
            findAll(
                Sort.by(
                    Sort.Order.desc(SORT_BY_PROPERTY)
                )
            ).forEachIndexed { index, it ->
                save(
                    it.apply {
                        rank = index + FIRST_PAGE_NUMBER
                    }
                )
            }
        }
    }
}
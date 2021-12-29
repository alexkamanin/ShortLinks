package ru.shurick.enterprise.short.link.data.dao

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.shurick.enterprise.short.link.data.dto.LinkDto

@Repository
interface LinkDao: PagingAndSortingRepository<LinkDto, Long> {

    fun findByLink(link: String): LinkDto?

    fun findByOriginal(original: String): LinkDto?

    @Modifying
    @Transactional
    @Query(value = "UPDATE links SET count = count + 1 WHERE link = :linkId", nativeQuery = true)
    fun increaseSessionCounter(linkId: String)
}
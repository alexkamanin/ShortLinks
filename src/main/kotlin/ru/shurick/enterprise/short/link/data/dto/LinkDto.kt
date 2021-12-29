package ru.shurick.enterprise.short.link.data.dto

import javax.persistence.*

@Entity
@Table(name = "links")
open class LinkDto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0,
    @Column(unique = true, nullable = false)
    open var link: String,
    @Column(columnDefinition = "TEXT", unique = true, nullable = false)
    open var original: String,
    @Column(nullable = false)
    open var rank: Int = 0,
    @Column(nullable = false)
    open var count: Int = 0
)
package com.siirro.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Achievement(
    title: String,
    description: String,
    achievedDate: LocalDate?,
    host: String,
    isActive: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null //null 허용한 이유? pk는 데이터베이스에 insert될 때 생성되므로, 인스턴스를 만든 순간에는 null이 당연함.

    var title: String = title
    var description: String = description
    var achievedDate: LocalDate? = achievedDate
    var host: String = host
    var isActive: Boolean = isActive
}
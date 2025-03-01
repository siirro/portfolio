package com.siirro.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class ProjectSkill : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_skill_id")
    var id: Long? = null

}
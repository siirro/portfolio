package com.siirro.portfolio.admin.context.skill.form

import com.siirro.portfolio.domain.entity.Skill

data class SkillForm(
    val name: String,
    val type: String,
    val isActive: Boolean
) {
    fun toEntity(): Skill {
        return Skill(
            name = this.name,
            type = this.type,
            isActive = this.isActive
        )
    }

    fun toEntity(id: Long): Skill {
        val skill = this.toEntity()
        skill.id = id

        return skill
    }
}
package com.siirro.portfolio.admin.context.achievement.service

import com.siirro.portfolio.admin.context.skill.form.SkillForm
import com.siirro.portfolio.admin.data.TableDTO
import com.siirro.portfolio.domain.entity.Skill
import com.siirro.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminSkillService(
    private val skillRepository: SkillRepository
) {
    fun getSkillTable(): TableDTO {
        val classInfo = Skill::class
        val entities = skillRepository.findAll()
        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: SkillForm) {
        val skill = form.toEntity()
        skillRepository.save(skill)
    }

    @Transactional
    fun update(id: Long, form: SkillForm) {
        val skill = form.toEntity(id)
        skillRepository.save(skill) //id가 있으면 자동으로 update 해줍니다.
    }
}
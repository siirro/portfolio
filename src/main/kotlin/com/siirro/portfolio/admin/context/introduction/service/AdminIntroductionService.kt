package com.siirro.portfolio.admin.context.achievement.service

import com.siirro.portfolio.admin.context.introduction.form.IntroductionForm
import com.siirro.portfolio.admin.data.TableDTO
import com.siirro.portfolio.domain.entity.Introduction
import com.siirro.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository
) {
    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()
        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: IntroductionForm) {
        val introduction = form.toEntity()
        introductionRepository.save(introduction)
    }

    @Transactional
    fun update(id: Long, form: IntroductionForm) {
        val introduction = form.toEntity(id)
        introductionRepository.save(introduction) //id가 있으면 자동으로 update 해줍니다.
    }
}
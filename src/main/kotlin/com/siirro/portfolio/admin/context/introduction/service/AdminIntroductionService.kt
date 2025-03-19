package com.siirro.portfolio.admin.context.achievement.service

import com.siirro.portfolio.admin.data.TableDTO
import com.siirro.portfolio.domain.entity.Introduction
import com.siirro.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository
) {
    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()
        return TableDTO.from(classInfo, entities)
    }
}
package com.siirro.portfolio.admin.context.achievement.service

import com.siirro.portfolio.admin.data.TableDTO
import com.siirro.portfolio.domain.entity.Achievement
import com.siirro.portfolio.domain.repository.LinkRepository
import org.springframework.stereotype.Service

@Service
class AdminLinkService(
    private val linkRepository: LinkRepository,
) {
    fun getLinkTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = linkRepository.findAll()
        return TableDTO.from(classInfo, entities)
    }
}
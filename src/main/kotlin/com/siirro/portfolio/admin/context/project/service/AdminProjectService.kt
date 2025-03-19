package com.siirro.portfolio.admin.context.experience.service

import com.siirro.portfolio.admin.data.TableDTO
import com.siirro.portfolio.admin.exception.AdminBadRequestException
import com.siirro.portfolio.domain.entity.Experience
import com.siirro.portfolio.domain.entity.ExperienceDetail
import com.siirro.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class AdminProjectService(
    private val projectRepository: ProjectRepository
) {
    fun getProjectTable(): TableDTO {
        val classInfo = Experience::class
        val entities = projectRepository.findAllByIsActive(true)
        return TableDTO.from(classInfo, entities, "details", "skills")
    }

    fun getProjectDetailTable(id: Long?): TableDTO {
        val classInfo = ExperienceDetail::class
        val entities = if (id != null) projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}
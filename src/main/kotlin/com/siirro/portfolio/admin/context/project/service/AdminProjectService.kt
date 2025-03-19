package com.siirro.portfolio.admin.context.experience.service

import com.siirro.portfolio.admin.context.project.form.ProjectForm
import com.siirro.portfolio.admin.data.TableDTO
import com.siirro.portfolio.admin.exception.AdminBadRequestException
import com.siirro.portfolio.domain.entity.Project
import com.siirro.portfolio.domain.entity.ProjectDetail
import com.siirro.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectService(
    private val projectRepository: ProjectRepository
) {
    fun getProjectTable(): TableDTO {
        val classInfo = Project::class
        val entities = projectRepository.findAllByIsActive(true)
        return TableDTO.from(classInfo, entities, "details", "skills")
    }

    fun getProjectDetailTable(id: Long?): TableDTO {
        val classInfo = ProjectDetail::class
        val entities = if (id != null) projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: ProjectForm) {
        val projectDetails = form.details
            ?.map { detail -> detail.toEntity() }
            ?.toMutableList()
        val project = form.toEntity()
        project.addDetails(projectDetails)

        projectRepository.save(project)
    }

    @Transactional
    fun update(id: Long, form: ProjectForm) {
        val project = projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터가 없습니다.") }
        project.update(
            name = form.name,
            description = form.description,
            startYear = form.startYear,
            startMonth = form.startMonth,
            endYear = form.endYear,
            endMonth = form.endMonth,
            isActive = form.isActive
        )

        val detailMap = project.details.map { it.id to it }.toMap()
        form.details?.forEach {
            val entity = detailMap.get(it.id)
            if (entity != null) {
                entity.update(
                    content = it.content,
                    url = it.url,
                    isActive = it.isActive
                )
            } else {
                project.details.add(it.toEntity())
            }
        }
    }
}
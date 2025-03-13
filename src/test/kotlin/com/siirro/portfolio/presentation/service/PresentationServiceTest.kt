package com.siirro.portfolio.presentation.service

import com.siirro.portfolio.domain.entity.Introduction
import com.siirro.portfolio.domain.entity.Link
import com.siirro.portfolio.domain.entity.Project
import com.siirro.portfolio.presentation.repository.PresentationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PresentationServiceTest {

    @InjectMocks
    lateinit var presentationService: PresentationService

    @Mock
    lateinit var presentationRepository: PresentationRepository

    val DATA_SIZE = 9

    @Test
    fun testGetIntroductions() {
        // given
        val introductions = mutableListOf<Introduction>()
        for (i in 1..DATA_SIZE) {
            val introduction = Introduction(content = "${i}", isActive = i % 2 == 0)
            introductions.add(introduction)
        }

        val activeIntroductions = introductions.filter { introduction -> introduction.isActive }

        Mockito.`when`(presentationRepository.getActiveIntroductions())
            .thenReturn(activeIntroductions)

        // when
        val introductionDTOs = presentationService.getIntroductions()

        // then
        assertThat(introductionDTOs).hasSize(DATA_SIZE / 2)
        for (introductionDTO in introductionDTOs) {
            assertThat(introductionDTO.content.toInt()).isEven()
        }
    }

    @Test
    fun testGetLinks() {

        // given
        val links = mutableListOf<Link>()
        for (i in 1..DATA_SIZE) {
            val link = Link(name = "${i}", content = "${i}", isActive = i % 2 != 0)
            links.add(link)
        }
        val activeLinks = links.filter { link -> link.isActive }

        Mockito.`when`(presentationRepository.getActiveLinks())
            .thenReturn(activeLinks)
        // when
        val linkDTOs = presentationService.getLinks()
        // then
        var expetedSize = DATA_SIZE / 2
        if (DATA_SIZE % 2 != 0) {
            expetedSize += 1
        }
        assertThat(linkDTOs).hasSize(expetedSize)
        for (linkDTO in linkDTOs) {
            assertThat(linkDTO.content.toInt()).isOdd()
        }
    }

    @Test
    fun testGetProjects() {
        // given
        val projects = mutableListOf<Project>()
        for (i in 1..DATA_SIZE) {
            val project = Project(
                name = "${i}",
                description = "${i}",
                startYear = i,
                endYear = i,
                startMonth = i,
                endMonth = i,
                isActive = i % 2 != 0
            )
            projects.add(project)
        }
        val activeProjects = projects.filter { project -> project.isActive }

        Mockito.`when`(presentationRepository.getActiveProjects())
            .thenReturn(activeProjects)
        // when
        val projectDTOs = presentationService.getProjects()
        // then
        assertThat(projectDTOs).hasSize(5)
        for (projectDTO in projectDTOs) {
            assertThat(projectDTO.name.toInt()).isOdd()
        }
    }
}
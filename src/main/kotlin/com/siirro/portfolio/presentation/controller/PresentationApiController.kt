package com.siirro.portfolio.presentation.controller

import com.siirro.portfolio.presentation.dto.IntroductionDTO
import com.siirro.portfolio.presentation.dto.LinkDTO
import com.siirro.portfolio.presentation.dto.ProjectDTO
import com.siirro.portfolio.presentation.dto.ResumeDTO
import com.siirro.portfolio.presentation.service.PresentationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// RestController : 내부적으로 responseBody 갖고있음
@RestController
@RequestMapping("/api")
class PresentationApiController(
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    fun test(): String {
        return "OK"
    }

    @GetMapping("/v1/introductions")
    fun getIntroductions(): List<IntroductionDTO> {
        return presentationService.getIntroductions()
    }

    @GetMapping("/v1/links")
    fun getLinks(): List<LinkDTO> {
        return presentationService.getLinks()
    }

    @GetMapping("/v1/resume")
    fun getResume(): ResumeDTO {
        return presentationService.getResume()
    }

    @GetMapping("/v1/projects")
    fun getProjects(): List<ProjectDTO> {
        val projectDTOs = presentationService.getProjects()
        println("Returned Projects: $projectDTOs")
        return projectDTOs
    }
}
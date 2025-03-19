package com.siirro.portfolio.admin.context.skill.controller

import com.siirro.portfolio.admin.context.achievement.service.AdminSkillService
import com.siirro.portfolio.admin.context.skill.form.SkillForm
import com.siirro.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/skill")
class AdminSkillApiController(
    private val adminSkillService: AdminSkillService
) {
    @PostMapping
    fun postSkill(@RequestBody @Validated form: SkillForm): ResponseEntity<Any> {
        adminSkillService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putSkill(@PathVariable id: Long, @RequestBody @Validated form: SkillForm): ResponseEntity<Any> {
        adminSkillService.update(id, form)

        return ApiResponse.successUpdate()
    }
}
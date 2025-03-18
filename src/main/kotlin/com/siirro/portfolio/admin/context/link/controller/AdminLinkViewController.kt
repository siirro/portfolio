package com.siirro.portfolio.admin.context.achievement.controller

import com.siirro.portfolio.admin.context.achievement.service.AdminLinkService
import com.siirro.portfolio.admin.data.FormElementDTO
import com.siirro.portfolio.admin.data.SelectFormElementDTO
import com.siirro.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = ["/admin/link"])
class AdminLinkViewController(
    private val adminLinkService: AdminLinkService
) {
    @GetMapping
    fun link(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("name", 2),
            TextFormElementDTO("content", 8),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("formElements", formElements)

        val table = adminLinkService.getLinkTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Resume"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", false)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"

    }
}
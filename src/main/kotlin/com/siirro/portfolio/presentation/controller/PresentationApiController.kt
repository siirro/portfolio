package com.siirro.portfolio.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// RestController : 내부적으로 responseBody 갖고있음
@RestController
@RequestMapping("/api")
class PresentationApiController {

    @GetMapping("/test")
    fun test(): String {
        return "OK"
    }
}
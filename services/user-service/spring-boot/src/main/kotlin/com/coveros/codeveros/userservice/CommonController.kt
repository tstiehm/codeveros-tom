package com.coveros.codeveros.userservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommonController {

    @GetMapping("/health-check")
    fun healthCheck() = "OK"

    @GetMapping("/api/docs")
    fun getApiDocs() = "HERE IS THE API"
}
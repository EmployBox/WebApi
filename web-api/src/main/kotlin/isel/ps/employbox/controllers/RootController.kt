package isel.ps.employbox.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import isel.ps.employbox.RootPage

@RestController
@RequestMapping("/")
class RootController {

    @GetMapping
    fun getRootPage() = RootPage()
}

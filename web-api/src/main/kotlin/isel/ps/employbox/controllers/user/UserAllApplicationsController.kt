package isel.ps.employbox.controllers.user

import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.service.ApplicationService

@RestController
@RequestMapping("/accounts/users/{id}/applications")
class UserAllApplicationsController(
        private val service: ApplicationService
) {

    @GetMapping
    fun getAllApplications(
            @PathVariable id: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int
    ) = service.findByAccountId(id, page, pageSize)
}

package isel.ps.employbox.controllers.account

import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.service.JobService

@RestController
@RequestMapping("/accounts/{accountId}/jobs/offered")
class AccountJobController(
        private val service: JobService
) {

    @GetMapping
    fun getOfferedJobs(
            @PathVariable accountId: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ) = service.findByAccountId(accountId, page, pageSize, orderColumn, orderClause)
}

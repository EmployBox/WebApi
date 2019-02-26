package isel.ps.employbox.controllers.jobs

import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.model.JobModel
import pt.isel.ps.employbox.service.ApplicationService
import pt.isel.ps.employbox.service.JobService

@RestController
@RequestMapping("/jobs")
class JobController(
        private val service: JobService,
        private val applicationService: ApplicationService
) {
    @GetMapping
    fun getAllJobs(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) location: String?,
            @RequestParam(required = false) title: String?,
            @RequestParam(required = false) wage: Int?,
            @RequestParam(required = false) offerType: String?,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ) = service.findBy(location, title, wage, offerType, orderColumn, orderClause, page, pageSize)

    @GetMapping("/{jid}")
    fun getJob(@PathVariable jid: Long) = service.retrieve(jid)

    @PostMapping
    fun createJob(@RequestBody job: JobModel, authentication: Authentication) = service.save(job)


    @PutMapping("/{jid}")
    fun updateJob(@PathVariable jid: Long, @RequestBody inJob: JobModel, authentication: Authentication) =
            if(jid != inJob.id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inJob)


    @DeleteMapping("/{jid}")
    fun deleteJob(@PathVariable jid: Long, authentication: Authentication) = service.remove(jid)

    @GetMapping("/{jid}/applications")
    fun getApplication(
            @PathVariable jid: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int
    ) = applicationService.findByJobId(jid, page, pageSize)
}

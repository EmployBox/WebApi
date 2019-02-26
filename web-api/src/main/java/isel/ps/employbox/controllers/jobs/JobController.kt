package isel.ps.employbox.controllers.jobs

import isel.ps.employbox.exceptions.BadRequestException
import isel.ps.employbox.model.input.InJob
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.model.output.OutJob
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH

@RestController
@RequestMapping("/jobs")
class JobController(private val jobService: JobService, private val applicationBinder: ApplicationBinder, private val jobBinder: JobBinder) {
    @GetMapping
    fun getAllJobs(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) location: String,
            @RequestParam(required = false) title: String,
            @RequestParam(required = false) wage: Int?,
            @RequestParam(required = false) offerType: String,
            @RequestParam(required = false) orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ): Mono<HalCollectionPage<Job>> {
        val future = jobService.getAllJobs(page, pageSize, location, title, wage, offerType, orderColumn, orderClause)
                .thenCompose({ jobCollectionPage -> jobBinder.bindOutput(jobCollectionPage, this.javaClass) })

        return Mono.fromFuture<HalCollectionPage<Job>>(future)
    }

    @GetMapping("/{jid}")
    fun getJob(@PathVariable jid: Long): Mono<OutJob> {
        val future = jobService.getJob(jid)
                .thenCompose(???({ jobBinder.bindOutput() }))

        return Mono.fromFuture<OutJob>(future)
    }


    @PostMapping
    fun createJob(@RequestBody job: InJob, authentication: Authentication): Mono<OutJob> {
        val newJob = jobBinder.bindInput(job)
        val future = jobService.createJob(newJob, authentication.name)
                .thenCompose(???({ jobBinder.bindOutput() }))

        return Mono.fromFuture<OutJob>(future)
    }


    @PutMapping("/{jid}")
    fun updateJob(@PathVariable jid: Long, @RequestBody inJob: InJob, authentication: Authentication): Mono<Void> {
        if (inJob.jobID != jid) throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        val updateJob = jobBinder.bindInput(inJob)

        return jobService.updateJob(updateJob, authentication.name)
    }


    @DeleteMapping("/{jid}")
    fun deleteJob(@PathVariable jid: Long, authentication: Authentication): Mono<Void> {
        return jobService.deleteJob(jid, authentication.name)
    }

    @GetMapping("/{jid}/applications")
    fun getApplication(
            @PathVariable jid: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int

    ): Mono<HalCollectionPage<*>> {
        return Mono.fromFuture(
                jobService.getApplication(jid, page, pageSize)
                        .thenCompose({ applicationCollectionPage -> applicationBinder.bindOutput(applicationCollectionPage, this.javaClass) })
        )
    }
}

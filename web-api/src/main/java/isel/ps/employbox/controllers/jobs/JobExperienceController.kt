package isel.ps.employbox.controllers.jobs


import isel.ps.employbox.ErrorMessages
import isel.ps.employbox.exceptions.BadRequestException
import isel.ps.employbox.model.binders.jobs.JobExperienceBinder
import isel.ps.employbox.model.entities.jobs.JobExperience
import isel.ps.employbox.model.input.InJobExperience
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.model.output.OutJobExperience
import isel.ps.employbox.services.JobService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors

@RestController
@RequestMapping("/jobs/{jid}/experiences")
class JobExperienceController(private val jobService: JobService, private val jobExperienceBinder: JobExperienceBinder) {

    @GetMapping
    fun getJobExperiences(
            @PathVariable jid: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int
    ): Mono<HalCollectionPage<JobExperience>> {
        val future = jobService.getJobExperiences(jid, page, pageSize)
                .thenCompose({ jobExperienceCollectionPage -> jobExperienceBinder.bindOutput(jobExperienceCollectionPage, this.javaClass, jid) })
        return Mono.fromFuture<HalCollectionPage<JobExperience>>(future)
    }

    @GetMapping("/{expId}")
    fun getJobExperience(
            @PathVariable jid: Long,
            @PathVariable expId: Long
    ): Mono<OutJobExperience> {
        val future = jobService.getJobExperience(jid, expId)
                .thenCompose(???({ jobExperienceBinder.bindOutput() }))

        return Mono.fromFuture<OutJobExperience>(future)
    }

    @PostMapping
    fun createJobExperiences(
            @PathVariable jid: Long,
            @RequestBody inJobExperiences: List<InJobExperience>,
            authentication: Authentication
    ): Mono<Void> {
        val jobExperiences = inJobExperiences.stream().map(Function<InJobExperience, Any> { jobExperienceBinder.bindInput() }).collect(Collectors.toList<Any>())
        val future = jobService.addJobExperienceToJob(jid, jobExperiences, authentication.name)
        return Mono.fromFuture<Void>(future)
    }

    @PutMapping("/{jxpId}")
    fun updateJobExperiences(
            @PathVariable jxpId: Long,
            @PathVariable jid: Long,
            @RequestBody inJobExperience: InJobExperience,
            authentication: Authentication
    ): Mono<Void> {
        if (jid != inJobExperience.jobId || jxpId != inJobExperience.jobExperienceId)
            throw BadRequestException(ErrorMessages.BAD_REQUEST_IDS_MISMATCH)

        val jobExperience = jobExperienceBinder.bindInput(inJobExperience)

        return jobService.updateJobExperience(jobExperience, authentication.name)
    }

    @DeleteMapping("/{jxpId}")
    fun deleteJobExperiences(
            @PathVariable jxpId: Long,
            @PathVariable jid: Long,
            authentication: Authentication): Mono<Void> {
        return jobService.deleteJobExperience(jxpId, jid, authentication.name)
    }

}

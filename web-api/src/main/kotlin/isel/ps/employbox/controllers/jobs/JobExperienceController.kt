package isel.ps.employbox.controllers.jobs

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.model.JobExperienceModel
import pt.isel.ps.employbox.service.JobExperienceService

@RestController
@RequestMapping("/jobs/{jid}/experiences")
class JobExperienceController(
        private val service: JobExperienceService
) {

    @GetMapping
    fun getJobExperiences(
            @PathVariable jid: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int
    ) = service.findByJobId(jid, page, pageSize)

    @GetMapping("/{expId}")
    fun getJobExperience(
            @PathVariable jid: Long,
            @PathVariable expId: Long
    ) = service.retrieve(expId)

    @PostMapping
    fun createJobExperiences(
            @PathVariable jid: Long,
            @RequestBody inJobExperience: JobExperienceModel,
            authentication: Authentication
    ) =
            if(jid != inJobExperience.job!!.id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inJobExperience)

    @PutMapping("/{jxpId}")
    fun updateJobExperiences(
            @PathVariable jxpId: Long,
            @PathVariable jid: Long,
            @RequestBody inJobExperience: JobExperienceModel,
            authentication: Authentication
    ) =
            if(jid != inJobExperience.job!!.id || jxpId != inJobExperience.id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inJobExperience)

    @DeleteMapping("/{jxpId}")
    fun deleteJobExperiences(
            @PathVariable jxpId: Long,
            @PathVariable jid: Long,
            authentication: Authentication) = service.remove(jxpId)

}

package isel.ps.employbox.controllers.user

import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.model.ApplicationModel
import pt.isel.ps.employbox.service.ApplicationService

@RestController
@RequestMapping("/accounts/users/{id}/jobs/{jid}/applications")
class UserJobApplicationController(
        private val service: ApplicationService
) {

    @GetMapping("/{apId}")
    fun getApplication(
            @PathVariable id: Long,
            @PathVariable jid: Long,
            @PathVariable apId: Long
    ) = service.retrieve(apId)

    @PostMapping
    fun createApplication(@PathVariable id: Long, @PathVariable jid: Long, @RequestBody inApplication: ApplicationModel, authentication: Authentication) =
            if(jid != inApplication.job!!.id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inApplication)

    @PutMapping("/{apId}")
    fun updateApplication(
            @PathVariable id: Long,
            @PathVariable jid: Long,
            @PathVariable apId: Long,
            @RequestBody inApplication: ApplicationModel,
            authentication: Authentication
    ) =
            if(apId != inApplication.account!!.id || jid != inApplication.job!!.id || apId != inApplication.id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inApplication)

    @DeleteMapping("/{apId}")
    fun deleteApplication(
            @PathVariable id: Long,
            @PathVariable jid: Long,
            @PathVariable apId: Long,
            authentication: Authentication
    ) = service.remove(apId)
}

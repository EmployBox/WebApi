package isel.ps.employbox.controllers.jobs


import isel.ps.employbox.ErrorMessages
import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.model.ScheduleModel
import pt.isel.ps.employbox.service.ScheduleService

@RestController
@RequestMapping("/jobs/{jobId}/schedules")
class ScheduleController(
        private val service: ScheduleService
) {

    @GetMapping
    fun getAllSchedules(
            @PathVariable jobId: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ) = service.findByJobId(jobId, page, pageSize, orderColumn, orderClause)

    @PutMapping("/{scheduleId}")
    fun updateSchedule(
            @PathVariable jobId: Long,
            @PathVariable scheduleId: Long,
            @RequestBody inSchedule: ScheduleModel,
            authentication: Authentication
    ) =
            if(scheduleId != inSchedule.id || jobId != inSchedule.job.id)
                throw BadRequestException(ErrorMessages.BAD_REQUEST_IDS_MISMATCH)
            else service.save(inSchedule)

    @PostMapping
    fun createSchedule(
            @PathVariable jobId: Long,
            @RequestBody inSchedule: ScheduleModel,
            authentication: Authentication
    ) =
            if(jobId != inSchedule.job.id)
                throw BadRequestException(ErrorMessages.BAD_REQUEST_IDS_MISMATCH)
            else service.save(inSchedule)

    @DeleteMapping("/{scheduleId}")
    fun deleteSchedule(
            @PathVariable jobId: Long,
            @PathVariable scheduleId: Long,
            authentication: Authentication
    ) = service.remove(scheduleId)

    @GetMapping("/{scheduleId}")
    fun getSchedule(
            @PathVariable scheduleId: Long
    ) = service.retrieve(scheduleId)
}

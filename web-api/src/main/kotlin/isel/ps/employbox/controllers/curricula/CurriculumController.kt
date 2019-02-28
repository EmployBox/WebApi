package isel.ps.employbox.controllers.curricula

import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.model.CurriculumModel
import pt.isel.ps.employbox.service.CurriculumService

@RestController
@RequestMapping("/accounts/users/{id}/curricula")
class CurriculumController(
        private val service: CurriculumService
) {

    @GetMapping
    fun getCurricula(
            @PathVariable id: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String

    ) = service.findByAccountId(id, page, pageSize, orderColumn, orderClause)

    @GetMapping("/{cid}")
    fun getCurriculum(@PathVariable id: Long, @PathVariable cid: Long) = service.retrieve(cid)

    @PostMapping
    fun createCurriculum(@PathVariable id: Long, @RequestBody inCurriculum: CurriculumModel, authentication: Authentication) =
            service.save(inCurriculum)

    @PutMapping("/{cid}")
    fun updateCurriculum(
            @PathVariable id: Long,
            @PathVariable cid: Long,
            @RequestBody inCurriculum: CurriculumModel,
            authentication: Authentication
    ) =
            if(cid != inCurriculum.id || inCurriculum.account!!.id != id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inCurriculum)

    @DeleteMapping("/{cid}")
    fun deleteCurriculum(@PathVariable id: Long, @PathVariable cid: Long, authentication: Authentication) =
            service.remove(cid)
}

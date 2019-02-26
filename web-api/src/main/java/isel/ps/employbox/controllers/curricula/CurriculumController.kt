package isel.ps.employbox.controllers.curricula

import isel.ps.employbox.exceptions.BadRequestException
import isel.ps.employbox.model.binders.curricula.CurriculumBinder
import isel.ps.employbox.model.entities.Curriculum
import isel.ps.employbox.model.input.curricula.childs.InCurriculum
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.model.output.OutCurriculum
import isel.ps.employbox.services.curricula.CurriculumService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH

@RestController
@RequestMapping("/accounts/users/{id}/curricula")
class CurriculumController(
        private val curriculumService: CurriculumService,
        private val curriculumBinder: CurriculumBinder
) {

    @GetMapping
    fun getCurricula(
            @PathVariable id: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String

    ): Mono<HalCollectionPage<Curriculum>> {
        val future = curriculumService.getCurricula(id, page, pageSize, orderColumn, orderClause)
                .thenCompose({ curriculumCollectionPage -> curriculumBinder.bindOutput(curriculumCollectionPage, this.javaClass, id) })
        return Mono.fromFuture<HalCollectionPage<Curriculum>>(future)
    }

    @GetMapping("/{cid}")
    fun getCurriculum(@PathVariable id: Long, @PathVariable cid: Long): Mono<OutCurriculum> {
        val future = curriculumService.getCurriculum(id, cid)
                .thenCompose(???({ curriculumBinder.bindOutput() }))
        return Mono.fromFuture<OutCurriculum>(future)
    }

    @PostMapping
    fun createCurriculum(@PathVariable id: Long, @RequestBody inCurriculum: InCurriculum, authentication: Authentication): Mono<OutCurriculum> {
        println(inCurriculum)
        val future = curriculumService.createCurriculum(id, curriculumBinder.bindInput(inCurriculum), authentication.name)
                .thenCompose(???({ curriculumBinder.bindOutput() }))
        return Mono.fromFuture<OutCurriculum>(future)
    }

    @PutMapping("/{cid}")
    fun updateCurriculum(
            @PathVariable id: Long,
            @PathVariable cid: Long,
            @RequestBody inCurriculum: InCurriculum,
            authentication: Authentication
    ): Mono<Void> {
        if (inCurriculum.accountId != id || inCurriculum.curriculumId != cid) throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        val curriculum = curriculumBinder.bindInput(inCurriculum)
        return curriculumService.updateCurriculum(curriculum, authentication.name)
    }

    @DeleteMapping("/{cid}")
    fun deleteCurriculum(@PathVariable id: Long, @PathVariable cid: Long, authentication: Authentication): Mono<Void> {
        return curriculumService.deleteCurriculum(id, cid, null, authentication.name)
    }
}

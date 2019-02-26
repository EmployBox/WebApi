package isel.ps.employbox.controllers

import isel.ps.employbox.exceptions.BadRequestException
import isel.ps.employbox.model.input.InCompany
import isel.ps.employbox.model.output.OutCompany
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH

@RestController
@RequestMapping("/accounts/companies")
class CompanyController(private val companyBinder: CompanyBinder, private val companyService: CompanyService) {

    @GetMapping
    fun getCompanies(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "5") pageSize: Int,
            @RequestParam(required = false) name: String,
            @RequestParam(required = false) specialization: String,
            @RequestParam(required = false) yearFounded: Int?,
            @RequestParam(required = false) location: String,
            @RequestParam(required = false) ratingLow: Int?,
            @RequestParam(required = false) ratingHigh: Int?,
            @RequestParam(required = false) orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ): Mono<HalCollectionPage<Company>> {
        val future = companyService.getCompanies(page, pageSize, ratingLow, ratingHigh, name, specialization, yearFounded, location, orderColumn, orderClause)
                .thenCompose({ companyCollectionPage -> companyBinder.bindOutput(companyCollectionPage, this.javaClass) })
        return Mono.fromFuture(future)
    }

    @GetMapping("/{cid}")
    fun getCompany(@PathVariable cid: Long): Mono<OutCompany> {
        val future = companyService.getCompany(cid)
                .thenCompose(???({ companyBinder.bindOutput() }))
        return Mono.fromFuture<OutCompany>(future)

    }

    @PostMapping
    fun createCompany(@RequestBody inCompany: InCompany): Mono<OutCompany> {
        val future = companyService.createCompany(companyBinder.bindInput(inCompany))
                .thenCompose(???({ companyBinder.bindOutput() }))
        return Mono.fromFuture<OutCompany>(future)
    }

    @PutMapping("/{id}")
    fun updateCompany(@PathVariable id: Long, @RequestBody inCompany: InCompany, authentication: Authentication): Mono<Void> {
        if (id != inCompany.accountId) throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        val company = companyBinder.bindInput(inCompany)
        return companyService.updateCompany(company, authentication.name)
    }

    @DeleteMapping("/{cid}")
    fun deleteCompany(@PathVariable cid: Long, authentication: Authentication): Mono<Void> {
        return companyService.deleteCompany(cid, authentication.name)
    }
}

package isel.ps.employbox.controllers

import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.model.CompanyModel
import pt.isel.ps.employbox.service.CompanyService

@RestController
@RequestMapping("/accounts/companies")
class CompanyController(
        private val service: CompanyService
) {

    @GetMapping
    fun getCompanies(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "5") pageSize: Int,
            @RequestParam(required = false) name: String?,
            @RequestParam(required = false) specialization: String?,
            @RequestParam(required = false) yearFounded: Int?,
            @RequestParam(required = false) location: String?,
            @RequestParam(required = false) ratingLow: Int?,
            @RequestParam(required = false) ratingHigh: Int?,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ) = service.findByParams(ratingLow, ratingHigh, name, specialization, yearFounded, null, orderColumn, orderClause, page, pageSize)

    @GetMapping("/{cid}")
    fun getCompany(@PathVariable cid: Long) = service.retrieve(cid)

    @PostMapping
    fun createCompany(@RequestBody inCompany: CompanyModel) = service.save(inCompany)

    @PutMapping("/{id}")
    fun updateCompany(@PathVariable id: Long, @RequestBody inCompany: CompanyModel, authentication: Authentication) =
        if(id != inCompany.id)
            throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        else service.save(inCompany)

    @DeleteMapping("/{cid}")
    fun deleteCompany(@PathVariable cid: Long, authentication: Authentication) = service.remove(cid)
}

package isel.ps.employbox.service

import isel.ps.base.service.BaseEntityService
import isel.ps.employbox.model.CompanyModel
import isel.ps.employbox.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface CompanyService : BaseEntityService<CompanyModel, Long> {
    fun findByParams(
            ratingLow: Int,
            ratingHigh: Int,
            name: String,
            specialization: String,
            yearFounded: Int,
            address: String,
            orderColumn: String,
            orderClause: String,
            page: Int,
            pageSize: Int) : PageModel<CompanyModel>
}
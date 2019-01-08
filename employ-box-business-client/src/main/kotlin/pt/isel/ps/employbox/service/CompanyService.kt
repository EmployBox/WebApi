package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.CompanyModel
import pt.isel.ps.employbox.model.PageModel

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
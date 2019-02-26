package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.base.model.PageModel
import pt.isel.ps.employbox.model.UserAccountModel

/**
 * @author tiago.ribeiro
 */
interface UserAccountService : BaseEntityService<UserAccountModel, Long> {
    fun findAllBy(
            name: String?,
            summary: String?,
            ratingLow: Int?,
            ratingHigh: Int?,
            orderColumn: String,
            orderClause: String,
            page: Int,
            pageSize: Int
    ) : PageModel<UserAccountModel>
}
package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.PageModel
import pt.isel.ps.employbox.model.AccountModel

interface AccountService : BaseEntityService<AccountModel, Long> {
    fun getByEmail(email: String) : AccountModel
    fun getFollowers(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) : PageModel<AccountModel>
    fun getFolloweds(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) : PageModel<AccountModel>
}
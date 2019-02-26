package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.CurriculumModel
import pt.isel.ps.base.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface CurriculumService : BaseEntityService<CurriculumModel, Long> {
    fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) : PageModel<CurriculumModel>

}
package pt.isel.ps.employbox.service

import pt.isel.ps.base.model.PageModel
import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.JobModel

/**
 * @author tiago.ribeiro
 */
interface JobService : BaseEntityService<JobModel, Long> {
    fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) : PageModel<JobModel>
    fun findBy(location: String?, title: String?, wage: Int?, offerType: String?, orderColumn: String, orderClause: String, page: Int, pageSize: Int) : PageModel<JobModel>
}
package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.ApplicationModel
import pt.isel.ps.base.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface ApplicationService : BaseEntityService<ApplicationModel, Long> {

    fun findByJobId(jobId: Long, page: Int, pageSize: Int) : PageModel<ApplicationModel>

    fun findByAccountId(accountId: Long, page: Int, pageSize: Int) : PageModel<ApplicationModel>

}
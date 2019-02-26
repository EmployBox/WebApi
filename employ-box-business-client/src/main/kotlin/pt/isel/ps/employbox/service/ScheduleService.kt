package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.base.model.PageModel
import pt.isel.ps.employbox.model.ScheduleModel

/**
 * @author tiago.ribeiro
 */
interface ScheduleService : BaseEntityService<ScheduleModel, Long> {
    fun findByJobId(jobId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) : PageModel<ScheduleModel>
}
package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.JobExperienceModel
import pt.isel.ps.base.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface JobExperienceService : BaseEntityService<JobExperienceModel, Long> {

    fun findByJobId(jobId: Long, page: Int, pageSize: Int) : PageModel<JobExperienceModel>



}
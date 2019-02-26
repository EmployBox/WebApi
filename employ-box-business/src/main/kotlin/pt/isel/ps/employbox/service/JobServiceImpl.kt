package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import pt.isel.ps.base.model.PageModel
import pt.isel.ps.employbox.business.JobBO
import pt.isel.ps.employbox.domain.jobs.Job
import pt.isel.ps.employbox.model.JobModel

/**
 * @author tiago.ribeiro
 */
@Component
class JobServiceImpl(
        override val business: JobBO, modelMapper: ModelMapper
) : AbstractBaseEntityService<Job, JobModel, Long>(modelMapper), JobService {
    override val modelClass = JobModel::class.java

    override val entityClass = Job::class.java
    override fun convertBeforeSave(model: JobModel) = convert(model, Job::class.java)

    override fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            convert(business.findByAccountId(accountId, page, pageSize, orderColumn, orderClause), PageModel::class.java)
                    .map { convert(it, JobModel::class.java) }

    override fun findBy(location: String?, title: String?, wage: Int?, offerType: String?, orderColumn: String, orderClause: String, page: Int, pageSize: Int) =
            convert(business.findBy(location, title, wage, offerType, orderColumn, orderClause, page, pageSize), PageModel::class.java)
                    .map { convert(it, JobModel::class.java) }
}
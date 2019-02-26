package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.business.JobExperienceBO
import pt.isel.ps.employbox.domain.jobs.JobExperience
import pt.isel.ps.employbox.model.JobExperienceModel
import pt.isel.ps.base.model.PageModel

/**
 * @author tiago.ribeiro
 */
@Component
class JobExperienceServiceImpl(
        override val business: JobExperienceBO, modelMapper: ModelMapper
) : AbstractBaseEntityService<JobExperience, JobExperienceModel, Long>(modelMapper), JobExperienceService {
    override val modelClass = JobExperienceModel::class.java
    override val entityClass = JobExperience::class.java

    override fun convertBeforeSave(model: JobExperienceModel) = convert(model, entityClass)

    override fun findByJobId(jobId: Long, page: Int, pageSize: Int) =
            convert(business.findByJobId(jobId, page, pageSize), PageModel::class.java)
                    .map { convert(it, modelClass) }
}
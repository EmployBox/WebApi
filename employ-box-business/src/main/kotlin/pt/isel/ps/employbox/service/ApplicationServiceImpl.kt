package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.business.ApplicationBO
import pt.isel.ps.employbox.domain.jobs.Application
import pt.isel.ps.employbox.model.ApplicationModel
import pt.isel.ps.base.model.PageModel

/**
 * @author tiago.ribeiro
 */
@Component
class ApplicationServiceImpl(
        override val business: ApplicationBO,
        modelMapper: ModelMapper
) : AbstractBaseEntityService<Application, ApplicationModel, Long>(modelMapper), ApplicationService {
    override val modelClass = ApplicationModel::class.java

    override val entityClass = Application::class.java
    override fun convertBeforeSave(model: ApplicationModel) = convert(model, entityClass)

    override fun findByJobId(jobId: Long, page: Int, pageSize: Int) =
            convert(business.findByJobId(jobId, page, pageSize), PageModel::class.java)
                    .map { convert(it, ApplicationModel::class.java) }

    override fun findByAccountId(accountId: Long, page: Int, pageSize: Int) =
            convert(business.findByAccountId(accountId, page, pageSize), PageModel::class.java)
                    .map { convert(it, ApplicationModel::class.java) }
}

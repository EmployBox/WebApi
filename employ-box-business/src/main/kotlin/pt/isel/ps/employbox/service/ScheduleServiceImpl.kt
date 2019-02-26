package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.business.ScheduleBO
import pt.isel.ps.employbox.domain.jobs.Schedule
import pt.isel.ps.base.model.PageModel
import pt.isel.ps.employbox.model.ScheduleModel

/**
 * @author tiago.ribeiro
 */
@Component
class ScheduleServiceImpl(
        override val business: ScheduleBO, modelMapper: ModelMapper
) : AbstractBaseEntityService<Schedule, ScheduleModel, Long>(modelMapper), ScheduleService {
    override val modelClass = ScheduleModel::class.java
    override val entityClass = Schedule::class.java

    override fun convertBeforeSave(model: ScheduleModel) = convert(model, entityClass)

    override fun findByJobId(jobId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            convert(business.finByJobId(jobId, page, pageSize, orderColumn, orderClause), PageModel::class.java)
                    .map { convert(it, modelClass) }
}
package pt.isel.ps.employbox.business

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.domain.jobs.Schedule
import pt.isel.ps.employbox.repository.ScheduleRepository

@Component
class ScheduleBO(
        override val repository: ScheduleRepository
) : AbstractBaseEntityBO<Schedule, Long>() {
    fun finByJobId(jobId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            repository.findByJob_Id(jobId, PageRequest.of(page, pageSize, Sort.Direction.fromString(orderClause), orderColumn))
}

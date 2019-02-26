package pt.isel.ps.employbox.business

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.domain.jobs.Application
import pt.isel.ps.employbox.repository.ApplicationRepository

@Component
class ApplicationBO(
        override val repository: ApplicationRepository
) : AbstractBaseEntityBO<Application, Long>() {
    fun findByJobId(jobId: Long, page: Int, pageSize: Int) =
            repository.findByJob_Id(jobId, PageRequest.of(page, pageSize))

    fun findByAccountId(accountId: Long, page: Int, pageSize: Int) =
            repository.findByAccount_Id(accountId, PageRequest.of(page, pageSize))
}

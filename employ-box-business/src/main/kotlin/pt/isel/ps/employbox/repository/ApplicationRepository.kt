package pt.isel.ps.employbox.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.jobs.Application

@Repository
interface ApplicationRepository : BaseRepository<Application, Long>{
    fun findByJob_Id(jobId: Long, pageable: Pageable) : Page<Application>
    fun findByAccount_Id(accountId: Long, pageable: Pageable) : Page<Application>
}

package pt.isel.ps.employbox.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.jobs.Schedule

@Repository
interface ScheduleRepository : BaseRepository<Schedule, Long> {
    fun findByJob_Id(jobId: Long, pageable: Pageable) : Page<Schedule>
}

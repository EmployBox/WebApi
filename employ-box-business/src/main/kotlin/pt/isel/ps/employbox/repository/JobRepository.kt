package pt.isel.ps.employbox.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.jobs.Job
import javax.persistence.criteria.Predicate

@Repository
interface JobRepository : BaseRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    fun findByAccount_Id(accountId: Long, pageable: Pageable): Page<Job>

}

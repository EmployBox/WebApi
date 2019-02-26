package pt.isel.ps.employbox.business

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.domain.jobs.JobExperience
import pt.isel.ps.employbox.repository.JobExperienceRepository

@Component
class JobExperienceBO(
        override val repository: JobExperienceRepository
) : AbstractBaseEntityBO<JobExperience, Long>() {
    fun findByJobId(jobId: Long, page: Int, pageSize: Int) =
            repository.findByJob_Id(jobId, PageRequest.of(page, pageSize))
}

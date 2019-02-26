package pt.isel.ps.employbox.business

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.jobs.Job
import pt.isel.ps.employbox.repository.JobRepository
import javax.persistence.criteria.Predicate

@Component
class JobBO(
        override val repository: JobRepository
) : AbstractBaseEntityBO<Job, Long>() {
    fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            repository.findByAccount_Id(accountId, PageRequest.of(page, pageSize, Sort.Direction.fromString(orderClause), orderColumn))

    fun findBy(location: String?, title: String?, wage: Int?, offerType: String?, orderColumn: String, orderClause: String, page: Int, pageSize: Int) =
            repository.findAll({ root, query, criteriaBuilder ->

                val locationPath = root.get<String>(Job::location.name)
                val titlePath = root.get<String>(Job::title.name)
                val wagePath = root.get<Int>(Job::wage.name)
                val offerTypePath = root.get<String>(Job::offerType.name)

                val predicates = mutableListOf<Predicate>()

                location?.let { predicates.add(criteriaBuilder.like(locationPath, it)) }
                title?.let { predicates.add(criteriaBuilder.like(titlePath, it)) }
                wage?.let { predicates.add(criteriaBuilder.equal(wagePath, it)) }
                offerType?.let { predicates.add(criteriaBuilder.equal(offerTypePath, it)) }

                criteriaBuilder.and(*predicates.toTypedArray())
            }, PageRequest.of(page, pageSize, Sort.Direction.fromString(orderClause), orderColumn))

}

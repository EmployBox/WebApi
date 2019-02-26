package pt.isel.ps.employbox.business

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.domain.UserAccount
import pt.isel.ps.employbox.repository.UserAccountRepository
import javax.persistence.criteria.Predicate

@Component
class UserAccountBO(
        override val repository: UserAccountRepository
) : AbstractBaseEntityBO<UserAccount, Long>() {
    fun findAllBy(name: String?, summary: String?, ratingLow: Int?, ratingHigh: Int?, orderColumn: String, orderClause: String, page: Int, pageSize: Int) =
            repository.findAll( { root, query, criteriaBuilder ->

                val namePath = root.get<String>(UserAccount::name.name)
                val summaryPath = root.get<String>(UserAccount::summary.name)
                val ratingPath = root.get<Int>(UserAccount::rating.name)

                val predicates = mutableListOf<Predicate>()

                name?.let { predicates.add(criteriaBuilder.like(namePath, it)) }
                summary?.let { predicates.add(criteriaBuilder.like(summaryPath, it)) }
                ratingHigh?.let { predicates.add(criteriaBuilder.lessThanOrEqualTo(ratingPath, it)) }
                ratingLow?.let { predicates.add(criteriaBuilder.greaterThanOrEqualTo(ratingPath, it)) }

                criteriaBuilder.and(*predicates.toTypedArray())

            }, PageRequest.of(page, pageSize, Sort.Direction.fromString(orderClause), orderColumn))

}

package pt.isel.ps.employbox.business

import pt.isel.ps.employbox.domain.Company
import pt.isel.ps.employbox.repository.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import javax.persistence.criteria.Predicate

/**
 * @author tiago.ribeiro
 */
@Component
class CompanyBO(
        @Autowired override val repository: CompanyRepository
) : AbstractBaseEntityBO<Company, Long>() {
    fun findByParams(ratingLow: Int?, ratingHigh: Int?, name: String?, specialization: String?, yearFounded: Int?, orderColumn: String, orderClause: String, page: Int, pageSize: Int)=
        repository.findAll({ root, query, criteriaBuilder ->
            val ratingPath = root.get<Int>(Company::rating.name)
            val namePath = root.get<String>(Company::name.name)
            val specializationPath = root.get<String>(Company::specialization.name)
            val yearFoundedPath = root.get<Int>(Company::yearFounded.name)

            val predicates = mutableListOf<Predicate>()
            if(ratingLow != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(ratingPath, ratingLow))
            if(ratingHigh != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(ratingPath, ratingHigh))
            if(name != null)
                predicates.add(criteriaBuilder.like(namePath, "%$name%"))
            if(specialization != null)
                predicates.add(criteriaBuilder.like(specializationPath, "%$specialization%"))
            if(yearFounded != null)
                predicates.add(criteriaBuilder.equal(yearFoundedPath, yearFounded))

            criteriaBuilder.and(*predicates.toTypedArray())
        }, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.valueOf(orderClause), orderColumn)))
}
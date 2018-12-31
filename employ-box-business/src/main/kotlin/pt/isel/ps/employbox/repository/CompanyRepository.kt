package pt.isel.ps.employbox.repository

import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.Company
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import javax.persistence.criteria.*

/**
 * @author tiago.ribeiro
 */
@Repository
interface CompanyRepository : BaseRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    fun findByParams(ratingLow: Int, ratingHigh: Int, name: String, specialization: String, yearFounded: Int, orderColumn: String, orderClause: String, page: Int, pageSize: Int) : Page<Company> {
        val companySearch = CompanySearch(ratingLow, ratingHigh, name, specialization, yearFounded)
        return findAll(companySearch, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.valueOf(orderClause), orderColumn)))
    }

    class CompanySearch(
            val ratingLow: Int?,
            val ratingHigh: Int?,
            val name: String?,
            val specialization: String?,
            val yearFounded: Int?
    ) : Specification<Company> {
        override fun toPredicate(root: Root<Company>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {

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

            return criteriaBuilder.and(*predicates.toTypedArray())
        }
    }
}


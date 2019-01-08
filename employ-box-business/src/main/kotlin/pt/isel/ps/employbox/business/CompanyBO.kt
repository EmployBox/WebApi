package pt.isel.ps.employbox.business

import pt.isel.ps.employbox.domain.Company
import pt.isel.ps.employbox.repository.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class CompanyBO(
        @Autowired override val repository: CompanyRepository
) : AbstractBaseEntityBO<Company, Long>() {
    fun findByParams(ratingLow: Int, ratingHigh: Int, name: String, specialization: String, yearFounded: Int, orderColumn: String, orderClause: String, page: Int, pageSize: Int) {
        repository.findByParams(ratingLow, ratingHigh, name, specialization, yearFounded, orderColumn, orderClause, page, pageSize)
    }
}
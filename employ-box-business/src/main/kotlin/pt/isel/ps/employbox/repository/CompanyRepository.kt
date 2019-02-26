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

}


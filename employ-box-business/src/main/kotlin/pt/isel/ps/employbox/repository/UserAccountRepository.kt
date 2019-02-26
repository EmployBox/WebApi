package pt.isel.ps.employbox.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.UserAccount
import javax.persistence.criteria.Predicate

interface UserAccountRepository : BaseRepository<UserAccount, Long>, JpaSpecificationExecutor<UserAccount> {

}

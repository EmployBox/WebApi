package pt.isel.ps.employbox.business

import pt.isel.ps.employbox.domain.Account
import pt.isel.ps.employbox.repository.AccountRepository
import org.omg.CosNaming.NamingContextPackage.NotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class AccountBO(
        @Autowired override val repository: AccountRepository
) : AbstractBaseEntityBO<Account, Long>() {

    fun getByEmail(email: String) = repository.findByEmail(email).orElseThrow { NotFound() }

    fun getFollowers(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            repository.findFollowers(accountId, PageRequest.of(page, pageSize, Sort.Direction.valueOf(orderClause), orderColumn))

    fun getFollowds(accountId: Long,  page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            repository.findFolloweds(accountId, PageRequest.of(page, pageSize, Sort.Direction.valueOf(orderClause), orderColumn))
}
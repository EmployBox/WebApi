package isel.ps.employbox.service

import isel.ps.base.MapperFactoryBean
import isel.ps.employbox.model.PageModel
import isel.ps.employbox.business.AccountBO
import isel.ps.employbox.domain.Account
import isel.ps.employbox.model.AccountModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class AccountServiceImpl(
        override val business: AccountBO,
        mapperFactoryBean: MapperFactoryBean
) : AbstractBaseEntityService<Account, AccountModel, Long>(mapperFactoryBean), AccountService {

    override val modelClass: Class<AccountModel> = AccountModel::class.java

    override val entityClass: Class<Account> = Account::class.java

    override fun getFollowers(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            handleResponse(business.getFollowers(accountId, page, pageSize, orderColumn, orderClause))

    override fun getFolloweds(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            handleResponse(business.getFollowds(accountId, page, pageSize, orderColumn, orderClause))

    override fun getByEmail(email: String): AccountModel =
            convert(business.getByEmail(email), AccountModel::class.java)

    override fun convertBeforeSave(model: AccountModel) = convert(model, Account::class.java)

    private fun handleResponse(page: Page<Account>) =
            convert(page, PageModel::class.java)
                    .map { convert(it, modelClass) }
}
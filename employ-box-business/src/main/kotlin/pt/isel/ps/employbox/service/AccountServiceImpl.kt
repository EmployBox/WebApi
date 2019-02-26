package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import pt.isel.ps.base.model.PageModel
import pt.isel.ps.employbox.business.AccountBO
import pt.isel.ps.employbox.domain.Account
import pt.isel.ps.employbox.model.AccountModel
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class AccountServiceImpl(
        override val business: AccountBO,
        modelMapper: ModelMapper
) : AbstractBaseEntityService<Account, AccountModel, Long>(modelMapper), AccountService {

    override val modelClass: Class<AccountModel> = AccountModel::class.java

    override val entityClass: Class<Account> = Account::class.java

    override fun getFollowers(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            handleResponse(business.getFollowers(accountId, page, pageSize, orderColumn, orderClause))

    override fun getFolloweds(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            handleResponse(business.getFollowds(accountId, page, pageSize, orderColumn, orderClause))

    override fun getByEmail(email: String): AccountModel =
            convert(business.getByEmail(email), AccountModel::class.java)

    override fun convertBeforeSave(model: AccountModel) = convert(model, Account::class.java)

    override fun checkIfFollowing(accountId: Long, accountToCheck: Long) =
            handleResponse(business.checkIfFollowing(accountId, accountToCheck))


    private fun handleResponse(page: Page<Account>) =
            convert(page, PageModel::class.java)
                    .map { convert(it, modelClass) }
}
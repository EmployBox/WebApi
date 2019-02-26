package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.business.UserAccountBO
import pt.isel.ps.employbox.domain.UserAccount
import pt.isel.ps.base.model.PageModel
import pt.isel.ps.employbox.model.UserAccountModel

/**
 * @author tiago.ribeiro
 */
@Component
class UserAccountServiceImpl(
        modelMapper: ModelMapper,
        override val business: UserAccountBO
) : AbstractBaseEntityService<UserAccount, UserAccountModel, Long>(modelMapper), UserAccountService {
    override fun findAllBy(name: String?, summary: String?, ratingLow: Int?, ratingHigh: Int?, orderColumn: String, orderClause: String, page: Int, pageSize: Int) =
            convert(business.findAllBy(name, summary, ratingLow, ratingHigh, orderColumn, orderClause, page, pageSize), PageModel::class.java)
                    .map { convert(it, modelClass) }

    override val modelClass = UserAccountModel::class.java
    override val entityClass = UserAccount::class.java

    override fun convertBeforeSave(model: UserAccountModel) =
            convert(model, UserAccount::class.java)
}
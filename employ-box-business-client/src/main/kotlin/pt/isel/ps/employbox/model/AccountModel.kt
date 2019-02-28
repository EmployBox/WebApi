package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import pt.isel.ps.employbox.common.AccountTypeEnum

open class AccountModel (
        id: Long? = null,
        var name: String? = null,
        var email: String? = null,
        var password: String? = null,
        var accountType: AccountTypeEnum? = null,
        var rating: Double? = null
) : IdBaseModel<Long>(id)
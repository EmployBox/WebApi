package pt.isel.ps.employbox.model

import pt.isel.ps.employbox.common.AccountTypeEnum

class UserAccountModel(
        id: Long? = null,
        name: String? = null,
        email: String? = null,
        password: String? = null,
        rating: Double? = null,
        var summary: String? = null,
        var photoUrl: String? = null
) : AccountModel(id, name, email, password, AccountTypeEnum.USER, rating)

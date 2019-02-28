package pt.isel.ps.employbox.model

import pt.isel.ps.employbox.common.AccountTypeEnum

/**
 * @author tiago.ribeiro
 */
class CompanyModel(
        id: Long? = null,
        email: String? = null,
        password: String? = null,
        rating: Double? = null,
        name: String? = null,
        var specialization: String? = null,
        var yearFounded: Int? = null,
        var logoUrl: String? = null,
        var webPageUrl: String? = null,
        var description: String? = null
) : AccountModel(id, name, email, password, AccountTypeEnum.COMPANY, rating)
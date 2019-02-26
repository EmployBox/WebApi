package pt.isel.ps.employbox.model

import pt.isel.ps.employbox.common.AccountTypeEnum

/**
 * @author tiago.ribeiro
 */
class CompanyModel(
        id: Long,
        email: String?,
        password: String?,
        rating: Double?,
        name: String?,
        var specialization: String?,
        var yearFounded: Int?,
        var logoUrl: String?,
        var webPageUrl: String?,
        var description: String?
) : AccountModel(id, name, email, password, AccountTypeEnum.COMPANY, rating)
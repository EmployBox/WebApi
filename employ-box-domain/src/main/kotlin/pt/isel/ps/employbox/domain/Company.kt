package pt.isel.ps.employbox.domain

import pt.isel.ps.employbox.common.AccountTypeEnum
import javax.persistence.Entity

@Entity
class Company(
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
) : Account(id, name, email, password, AccountTypeEnum.COMPANY, rating)
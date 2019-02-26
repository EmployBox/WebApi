package pt.isel.ps.employbox.model

import pt.isel.ps.employbox.common.AccountTypeEnum

class UserAccountModel(
        id: Long,
        name: String?,
        email: String?,
        password: String?,
        rating: Double?,
        var summary: String?,
        var photoUrl: String?
) : AccountModel(id, name, email, password, AccountTypeEnum.USER, rating) {
    lateinit var curricula: List<CurriculumModel>
    lateinit var applications: List<ApplicationModel>
}

package pt.isel.ps.employbox.domain

import pt.isel.ps.employbox.common.AccountTypeEnum
import pt.isel.ps.employbox.domain.jobs.Application
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class UserAccount(
        id: Long,
        name: String?,
        email: String?,
        password: String?,
        rating: Double?,
        var summary: String?,
        var photoUrl: String?
) : Account(id, name, email, password, AccountTypeEnum.USER, rating) {
    @OneToMany(mappedBy = "account")
    lateinit var curricula: MutableList<Curriculum>
    @OneToMany(mappedBy = "account")
    lateinit var applications: MutableList<Application>
}
package isel.ps.employbox.domain

import isel.ps.employbox.domain.jobs.Application
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
) : Account(id, name, email, password, "USR", rating) {
    @OneToMany(mappedBy = "account")
    lateinit var curricula: List<Curriculum>
    @OneToMany(mappedBy = "account")
    lateinit var applications: List<Application>
}
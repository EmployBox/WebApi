package pt.isel.ps.employbox.domain

import pt.isel.ps.employbox.common.AccountTypeEnum
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@Embeddable
class Moderator(
        id: Long,
        name: String?,
        email: String?,
        password: String?,
        rating: Double?
): Account(id, name, email, password, AccountTypeEnum.MODERATOR, rating) {
    //@OneToMany(mappedBy = "moderator")
    //lateinit var ratingsModerated: MutableList<Rating>
}
package isel.ps.employbox.domain

import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Moderator(
        id: Long,
        name: String?,
        email: String?,
        password: String?,
        rating: Double?
): Account(id, name, email, password, "MOD", rating) {
    @OneToMany(mappedBy = "moderator")
    lateinit var ratingsModerated: List<Rating>
}
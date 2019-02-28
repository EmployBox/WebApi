package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.IdBaseEntity
import pt.isel.ps.employbox.common.AccountTypeEnum
import pt.isel.ps.employbox.domain.jobs.Job
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
class Account(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        var name: String?,
        var email: String?,
        var password: String?,
        var accountType: AccountTypeEnum,
        var rating: Double?
) : IdBaseEntity<Long>() {
        @OneToMany(mappedBy = "account")
        lateinit var offeredJobs: MutableList<Job>
        @OneToMany(mappedBy = "accountFrom")
        lateinit var comments: MutableList<Comment>
        @ManyToMany
        @JoinTable(
                name = "Follows",
                joinColumns = [JoinColumn(name = "accountIdFollowed" )],
                inverseJoinColumns = [JoinColumn(name = "accountIdFollower")]
        )
        lateinit var following: MutableList<Account>
        @ManyToMany
        @JoinTable(
                name = "Follows",
                joinColumns = [JoinColumn(name = "accountIdFollower")],
                inverseJoinColumns = [JoinColumn(name = "accountIdFollowed")]
        )
        lateinit var followers: MutableList<Account>
        @OneToMany(mappedBy = "accountFirst")
        lateinit var chats: MutableList<Chat>
        @OneToMany(mappedBy = "id.accountTo")
        lateinit var ratings: MutableList<Rating>
}
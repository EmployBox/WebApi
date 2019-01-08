package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.VersionedBaseEntity
import pt.isel.ps.employbox.domain.jobs.Job
import javax.persistence.*

@Entity
class Account(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long,
        var name: String?,
        var email: String?,
        var password: String?,
        var accountType: String?,
        var rating: Double?
) : VersionedBaseEntity<Long>() {
        @OneToMany(mappedBy = "account")
        lateinit var offeredJobs: List<Job>
        @OneToMany(mappedBy = "accountFrom")
        lateinit var comments: List<Comment>
        @ManyToMany
        @JoinTable(
                name = "Follows",
                joinColumns = [JoinColumn(name = "accountIdFollowed" )],
                inverseJoinColumns = [JoinColumn(name = "accountIdFollower")]
        )
        lateinit var following: List<Account>
        @ManyToMany
        @JoinTable(
                name = "Follows",
                joinColumns = [JoinColumn(name = "accountIdFollower")],
                inverseJoinColumns = [JoinColumn(name = "accountIdFollowed")]
        )
        lateinit var followers: List<Account>
        @OneToMany(mappedBy = "accountFirst")
        lateinit var chats: List<Chat>
        @OneToMany(mappedBy = "accountTo")
        lateinit var ratings: List<Rating>
}
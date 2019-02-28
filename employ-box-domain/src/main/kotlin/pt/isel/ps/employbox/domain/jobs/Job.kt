package pt.isel.ps.employbox.domain.jobs

import pt.isel.ps.base.entity.IdBaseEntity
import pt.isel.ps.employbox.domain.Account
import java.util.*
import javax.persistence.*

@Entity
class Job(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        var title: String?,
        var address: String?,
        var wage: Int?,
        var description: String?,
        var offerBeginDate: Date?,
        var offerEndDate: Date?,
        var offerType: String?,
        var type: String?,
        var location: String?,
        @ManyToOne
        @JoinColumn(name = "accountId")
        var account: Account?
) : IdBaseEntity<Long>() {
    @OneToMany(mappedBy = "job")
    lateinit var applications: MutableList<Application>
    @OneToMany(mappedBy = "job")
    lateinit var experiences: MutableList<JobExperience>
    @OneToMany(mappedBy = "job")
    lateinit var schedules: MutableList<Schedule>
}
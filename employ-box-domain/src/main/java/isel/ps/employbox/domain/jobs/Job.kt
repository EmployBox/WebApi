package isel.ps.employbox.domain.jobs

import isel.ps.base.entity.VersionedBaseEntity
import isel.ps.employbox.domain.Account
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
        @ManyToOne
        @JoinColumn(name = "accountId")
        var account: Account?
) : VersionedBaseEntity<Long?>() {
    @OneToMany(mappedBy = "job")
    lateinit var applications: List<Application>
    @OneToMany(mappedBy = "job")
    lateinit var experiences: List<JobExperience>
    @OneToMany(mappedBy = "job")
    lateinit var schedules: List<Schedule>
}
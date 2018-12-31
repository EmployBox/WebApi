package isel.ps.employbox.domain.jobs

import isel.ps.base.entity.VersionedBaseEntity
import isel.ps.employbox.domain.Account
import isel.ps.employbox.domain.Curriculum
import isel.ps.employbox.model.entities.jobs.Job
import java.util.*
import javax.persistence.*

@Entity
class Application(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        var datetime: Date?,
        @ManyToOne
        @JoinColumn(name = "jobId")
        var job: Job?,
        @ManyToOne
        @JoinColumn(name = "accountId")
        var account: Account?,
        @ManyToOne
        @JoinColumn(name = "curriculumId")
        var curriculum: Curriculum?
) : VersionedBaseEntity<Long?>()
package isel.ps.employbox.domain.jobs

import isel.ps.base.entity.VersionedBaseEntity
import javax.persistence.*

@Entity
class JobExperience (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        var competence: String?,
        var years: Int?,
        @ManyToOne
        @JoinColumn(name = "jobId")
        var job: Job?
) : VersionedBaseEntity<Long?>()
package pt.isel.ps.employbox.domain.jobs

import pt.isel.ps.base.entity.IdBaseEntity
import javax.persistence.*

@Entity
class JobExperience (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        var competence: String?,
        var years: Int?,
        @ManyToOne
        @JoinColumn(name = "jobId")
        var job: Job
) : IdBaseEntity<Long>()
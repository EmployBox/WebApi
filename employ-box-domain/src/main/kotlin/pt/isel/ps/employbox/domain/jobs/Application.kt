package pt.isel.ps.employbox.domain.jobs

import pt.isel.ps.base.entity.IdBaseEntity
import pt.isel.ps.employbox.domain.Account
import pt.isel.ps.employbox.domain.Curriculum
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
) : IdBaseEntity<Long>()
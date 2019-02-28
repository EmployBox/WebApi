package pt.isel.ps.employbox.domain.jobs

import pt.isel.ps.base.entity.IdBaseEntity
import java.util.*
import javax.persistence.*

@Entity
class Schedule(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        var date: Date?,
        var startHour: Date?,
        var endHour: Date?,
        var repeats: String?,
        @ManyToOne
        @JoinColumn(name = "jobId")
        var job: Job?
) : IdBaseEntity<Long>()
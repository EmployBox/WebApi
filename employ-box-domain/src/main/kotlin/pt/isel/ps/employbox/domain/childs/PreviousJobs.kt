package pt.isel.ps.employbox.domain.childs

import pt.isel.ps.base.entity.VersionedBaseEntity
import pt.isel.ps.employbox.domain.Account
import pt.isel.ps.employbox.domain.Curriculum
import java.util.*
import javax.persistence.*

@Entity
class PreviousJobs(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        @ManyToOne
        @JoinColumn(name = "curriculumId")
        override var curriculum: Curriculum,
        @ManyToOne
        @JoinColumn(name = "accountId")
        override var account: Account,
        var beginDate: Date?,
        var endDate: Date?,
        var companyName: String?,
        var workLoad: String?,
        var role: String?
) : VersionedBaseEntity<Long?>(), CurriculumChild
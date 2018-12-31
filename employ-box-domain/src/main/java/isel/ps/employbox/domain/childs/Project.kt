package isel.ps.employbox.domain.childs

import isel.ps.base.entity.VersionedBaseEntity
import isel.ps.employbox.domain.Account
import isel.ps.employbox.domain.Curriculum
import javax.persistence.*

@Entity
class Project(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        @ManyToOne
        @JoinColumn(name = "curriculumId")
        override var curriculum: Curriculum,
        @ManyToOne
        @JoinColumn(name = "accountId")
        override var account: Account,
        var name: String?,
        var description: String?
) : VersionedBaseEntity<Long?>(), CurriculumChild
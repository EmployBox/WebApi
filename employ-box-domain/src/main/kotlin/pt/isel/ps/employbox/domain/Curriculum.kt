package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.VersionedBaseEntity
import pt.isel.ps.employbox.domain.childs.AcademicBackground
import pt.isel.ps.employbox.domain.childs.CurriculumExperience
import pt.isel.ps.employbox.domain.childs.PreviousJobs
import pt.isel.ps.employbox.domain.childs.Project
import pt.isel.ps.employbox.domain.jobs.Application
import javax.persistence.*

@Entity
class Curriculum(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long,
        var title: String,
        @ManyToOne
        @JoinColumn(name = "accountId")
        var account: Account
): VersionedBaseEntity<Long>() {
    @OneToMany(mappedBy = "curriculum")
    lateinit var previousJobs: MutableList<PreviousJobs>
    @OneToMany(mappedBy = "curriculum")
    lateinit var academicBackground: MutableList<AcademicBackground>
    @OneToMany(mappedBy = "curriculum")
    lateinit var projects: MutableList<Project>
    @OneToMany(mappedBy = "curriculum")
    lateinit var experiences: MutableList<CurriculumExperience>
    @OneToMany(mappedBy = "curriculum")
    lateinit var applications: MutableList<Application>
}
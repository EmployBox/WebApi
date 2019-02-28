package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

class CurriculumModel(
        id: Long? = null,
        var title: String? = null,
        var account: AccountModel? = null
) : IdBaseModel<Long>(id) {
    lateinit var previousJobs: List<PreviousJobsModel>
    lateinit var academicBackground: List<AcademicBackgroundModel>
    lateinit var projects: List<ProjectModel>
    lateinit var experiences: List<CurriculumExperienceModel>
    lateinit var applications: List<ApplicationModel>
}
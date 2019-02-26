package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

class JobExperienceModel(
        id: Long,
        var competence: String?,
        var years: Int?,
        var job: JobModel
) : IdBaseModel<Long>(id) {

}

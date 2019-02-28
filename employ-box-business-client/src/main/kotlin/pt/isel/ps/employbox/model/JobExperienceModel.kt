package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

class JobExperienceModel(
        id: Long? = null,
        var competence: String? = null,
        var years: Int? = null,
        var job: JobModel? = null
) : IdBaseModel<Long>(id) {

}

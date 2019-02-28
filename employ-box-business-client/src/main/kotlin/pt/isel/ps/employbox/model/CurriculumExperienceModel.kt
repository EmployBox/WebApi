package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

class CurriculumExperienceModel(
        id: Long? = null,
        var competence: String? = null,
        var years: Int? = null
) : IdBaseModel<Long>(id)

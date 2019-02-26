package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

class CurriculumExperienceModel(
        id: Long,
        var competence: String?,
        var years: Int?
) : IdBaseModel<Long>(id)

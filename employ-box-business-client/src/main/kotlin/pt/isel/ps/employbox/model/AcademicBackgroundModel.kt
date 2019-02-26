package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

class AcademicBackgroundModel(
        id: Long,
        var endDate: Date?,
        var studyArea: String?,
        var institution: String?,
        var degreeObtained: String?
) : IdBaseModel<Long>(id)
package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

class AcademicBackgroundModel(
        id: Long? = null,
        var endDate: Date? = null,
        var studyArea: String? = null,
        var institution: String? = null,
        var degreeObtained: String? = null
) : IdBaseModel<Long>(id)
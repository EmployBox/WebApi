package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

class PreviousJobsModel(
        id: Long? = null,
        var beginDate: Date? = null,
        var endDate: Date? = null,
        var companyName: String? = null,
        var workLoad: String? = null,
        var role: String? = null
) : IdBaseModel<Long>(id)




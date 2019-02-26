package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

class PreviousJobsModel(
        id: Long,
        var beginDate: Date?,
        var endDate: Date?,
        var companyName: String?,
        var workLoad: String?,
        var role: String?
) : IdBaseModel<Long>(id)




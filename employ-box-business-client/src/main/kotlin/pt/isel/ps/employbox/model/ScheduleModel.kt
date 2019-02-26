package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import pt.isel.ps.employbox.domain.jobs.Job
import java.util.*

class ScheduleModel(
        id: Long,
        var date: Date?,
        var startHour: Date?,
        var endHour: Date?,
        var repeats: String?,
        var job: Job
) : IdBaseModel<Long>(id) {

}

package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

class ScheduleModel(
        id: Long? = null,
        var date: Date? = null,
        var startHour: Date? = null,
        var endHour: Date? = null,
        var repeats: String? = null,
        var job: JobModel? = null
) : IdBaseModel<Long>(id) {

}

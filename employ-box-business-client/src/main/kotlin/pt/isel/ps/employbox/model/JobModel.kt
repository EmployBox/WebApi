package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

/**
 * @author tiago.ribeiro
 */
class JobModel(
        id: Long,
        var title: String?,
        var address: String?,
        var wage: Int?,
        var description: String?,
        var offerBeginDate: Date?,
        var offerEndDate: Date?,
        var offerType: String?,
        var type: String?,
        var location: String?,
        var account: AccountModel?
) : IdBaseModel<Long>(id) {
    lateinit var applications: List<ApplicationModel>
    lateinit var experiences: List<JobExperienceModel>
    lateinit var schedules: List<ScheduleModel>
}
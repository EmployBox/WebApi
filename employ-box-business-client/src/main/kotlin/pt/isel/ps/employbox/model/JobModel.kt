package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

/**
 * @author tiago.ribeiro
 */
class JobModel(
        id: Long? = null,
        var title: String? = null,
        var address: String? = null,
        var wage: Int? = null,
        var description: String? = null,
        var offerBeginDate: Date? = null,
        var offerEndDate: Date? = null,
        var offerType: String? = null,
        var type: String? = null,
        var location: String? = null,
        var account: AccountModel? = null
) : IdBaseModel<Long>(id) {
    lateinit var applications: List<ApplicationModel>
    lateinit var experiences: List<JobExperienceModel>
    lateinit var schedules: List<ScheduleModel>
}